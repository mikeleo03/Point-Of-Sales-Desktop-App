package main.Plugin;
import java.util.*;
import java.lang.reflect.*;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class PluginLoader {
    // Returns an arraylist of class names in a JarInputStream
    private ArrayList<String> getClassNamesFromJar(JarInputStream jarFile) throws Exception {
        ArrayList<String> classNames = new ArrayList<>();
        try {
            //JarInputStream jarFile = new JarInputStream(jarFileStream);
            JarEntry jar;
    
            //Iterate through the contents of the jar file
            while (true) {
                jar = jarFile.getNextJarEntry();
                if (jar == null) {
                    break;
                }
                //Pick file that has the extension of .class
                if ((jar.getName().endsWith(".class"))) {
                    String className = jar.getName().replaceAll("/", "\\.");
                    String myClass = className.substring(0, className.lastIndexOf('.'));
                    classNames.add(myClass);
                }
            }
        } catch (Exception e) {
            throw new Exception("Error while getting class names from jar", e);
        }
        return classNames;
    }
 
    // Returns an arraylist of class names in a JarInputStream
    // Calls the above function by converting the jar path to a stream
    private ArrayList<String> getClassNamesFromJar(String jarPath) throws Exception {
        return getClassNamesFromJar(new JarInputStream(new FileInputStream(jarPath)));
    }

    // get an arraylist of all the loaded classes in a jar file
    private ArrayList<Class> loadJarFile(String filePath) throws Exception {
    
        ArrayList<Class> availableClasses = new ArrayList<>();
        
        ArrayList<String> classNames = getClassNamesFromJar(filePath);
        File f = new File(filePath);
    
        URLClassLoader classLoader = new URLClassLoader(new URL[]{f.toURI().toURL()});
        for (String className : classNames) {
            try {
                String name = "main.Plugin." + className;
                Class cc = classLoader.loadClass(name);
                final List<Class> implementedItf = Arrays.asList(cc.getInterfaces());
                for (Class inter : implementedItf) {
                    if (inter.getName().contains("PluginInterface")) {
                        availableClasses.add(cc);
                        break;
                    }
                }
            } catch (ClassNotFoundException e) {
                continue;
            }
        }
        return availableClasses;
    }

    /* public void loadPlugin (String pluginPath) throws Exception {
        try {
            ArrayList<Class> classes = loadJarFile(pluginPath);
            classes.forEach(c -> {
                try {
                    Method s = c.getMethod("start", String.class);
                    BasePlugin p = (BasePlugin) c.newInstance();
                    s.invoke(p, "test");
                } catch (Exception e) {

                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    } */

    public void loadPlugin (String pluginPath, InterfacePage mp) throws Exception {
        try {
            ArrayList<Class> classes = loadJarFile(pluginPath);
            for (Class c : classes) {
                try {
                    Method m = c.getMethod("onLoad");
                    Constructor<?> constructor = c.getDeclaredConstructor(InterfacePage.class);
                    PluginInterface p = (PluginInterface) constructor.newInstance(mp);
                    m.invoke(p);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }    
}