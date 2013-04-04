package visagebinder;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
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
            //Class<?> c = DropShadow.class;
            CustomClass customclass = new CustomClass();
            customclass.setClassName(c.getSimpleName());
            customclass.setPackageName(c.getPackage().getName());
            customclass.setParentClass(c.getSuperclass().getSimpleName());

            customclass.setPropertyList(new ArrayList<String>());
            Field[] fields = c.getDeclaredFields();
            initFields(fields, c, customclass);

            System.out.println(customclass);
                Scanner s = new Scanner(System.in);
                s.next();
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
            customclass.getPropertyList().add(p.toString());

        }
    }
}
