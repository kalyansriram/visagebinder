package visagebinder;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.effect.DropShadow;

public class Visagebinder {

    public static void main(String[] args) {

        InputStream in = (InputStream) Visagebinder.class.getResourceAsStream("packages.txt");
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader b = new BufferedReader(isr);
        
        Class<?> c=null;
        String className=null;
        try {
            while((className=b.readLine())!=null)
            {
            c= Class.forName(className);
            //CustomClass Contains whole blueprint of the Class
            CustomClass customclass = new CustomClass(c.getSimpleName(),c.getPackage().getName(),c.getSuperclass().getSimpleName(),new ArrayList<String>());
            Field[] fields = c.getDeclaredFields();
            //intialise all the fields to customclass
            initFields(fields, c, customclass);
            System.out.println(customclass);
            File dir = new File(customclass.getPackageName().replace('.', '/'));
            dir.mkdirs();
            File f = new File(dir,customclass.getClassName()+".visage");
                FileWriter fw = new FileWriter(f);
                fw.write(customclass.toString());
                fw.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Visagebinder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Visagebinder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static public void initFields(Field[] fields, Class c, CustomClass customclass) {
        for (Field field : fields) {
            //Initialise the property name and data type
            Property p = new Property();
            p.setPropertyName(field.getName());
            p.setPropertyType(field.getType().getSimpleName());
            p.setClassName(c.getSimpleName());
            //Skip Boolean Types
            if (!field.getType().equals(boolean.class)) {
                try {
                    //create getter name
                    String gettersName = p.getGettersName(p.getPropertyName());
                    //Get Default Value by creating new object
                    Method thisMethod = c.getDeclaredMethod(gettersName, null);
                    Object defaultValue = thisMethod.invoke(c.cast(c.newInstance()), null);
                    if (defaultValue == null)
                        continue;
                    p.setDefaultValue(defaultValue.toString());
                    
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
                   if(ex instanceof NoSuchMethodException)
                    continue;
                   else
                       Logger.getLogger(Visagebinder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            customclass.getPropertyList().add(p.toString());

        }
    }
}
