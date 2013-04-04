package visagebinder;

import java.util.Iterator;
import java.util.List;

public class CustomClass {

    List<String> propertyList;
    String className;
    String parentClass;
    String packageName;

    public String getClassName() {
        return className;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getParentClass() {
        return parentClass;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setParentClass(String parentClass) {
        this.parentClass = parentClass;
    }
    
    
    
    
    
    public List<String> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<String> propertyList) {
        this.propertyList = propertyList;
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        String copyRight = "/*\n" +
" * Copyright (c) 2010-2011, Visage Project\n" +
" * All rights reserved.\n" +
" *\n" +
" * Redistribution and use in source and binary forms, with or without\n" +
" * modification, are permitted provided that the following conditions are met:\n" +
" * 1. Redistributions of source code must retain the above copyright notice,\n" +
" *    this list of conditions and the following disclaimer.\n" +
" * 2. Redistributions in binary form must reproduce the above copyright notice,\n" +
" *    this list of conditions and the following disclaimer in the documentation\n" +
" *    and/or other materials provided with the distribution.\n" +
" * 3. Neither the name Visage nor the names of its contributors may be used\n" +
" *    to endorse or promote products derived from this software without\n" +
" *    specific prior written permission.\n" +
" *\n" +
" * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \"AS IS\"\n" +
" * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE\n" +
" * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE\n" +
" * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE\n" +
" * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR\n" +
" * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF\n" +
" * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS\n" +
" * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN\n" +
" * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)\n" +
" * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE\n" +
" * POSSIBILITY OF SUCH DAMAGE.\n" +
" */\n";
        s.append(copyRight);
        String temp = "package visage."+packageName+";\n"+"public class "+className+" extends "+parentClass+"{\n";
        s.append(temp);
        String author = "/**\n" +
" * @author Kalyan & SivaBalan <kalyanthedeveloper@gmail.com,"
                + "sivabalanb.92@gmail.com>\n" +
" */\n";
        s.append(author);
        for (Iterator<String> it = propertyList.iterator(); it.hasNext();) { 
            s.append(it.next()).append("\n");
        }
        s.append("}");
        return s.toString();
    }
}
