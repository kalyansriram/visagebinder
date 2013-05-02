package visagebinder;

public class Property {
   String propertyName;
   String propertyType;
   String defaultValue;
   String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
   
    
    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
           
   

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public String getPropertyName() {
        return propertyName;
    }

    @Override
    public String toString() {
        String propertyvalue=null;
        
        propertyvalue = "public var " + propertyName + ":" + propertyType + " on replace{\n" +
"        if(isInitialized(this) or " + propertyName + " !="+defaultValue + "){\n" +
"            wrapped"+className+".set"+indexUpperCase(propertyName)+"(" + propertyName +");\n" +
"        }\n" +
"}";
        
        return propertyvalue; 
    }
  
    
    public String indexUpperCase(String value){
       char[] stringArray = value.toCharArray();
       stringArray[0] = Character.toUpperCase(stringArray[0]);
       value = new String(stringArray);
       return value;
    }
   
    public String getGettersName(String value){
        return "get"+indexUpperCase(value);
        
    }
    
    
    
}
