package com.quadratic.rap.common;

public enum EnumSessionConstants
{
    LOGIN_INTO_APPLICATION("Login into application"),
    
    EDIT_DATA_IMPORT_DEFINITION( "Edited the Data Import Definition" ),
    
    CREATE_DATA_IMPORT_DEFINITION("Created new Data Import Definition"),
    
    CREATE_PACKAGE("Created a new Package"), 
    
    ENTER_INTO_DASHBOARD("Entered into Dash Board"),
    
    CLICKED_ON_DATA_IMPORT_MANAGEMENT("Clicked on Data Import Managment"),
    
    ENTERED_INTO_DATA_IMPORT_MANAGEMENT("Entered into Data Import Managment"),
    
    CLICKED_ON_CREATE_PACKAGE("Clicked on Create Package ");
    
    
    
   private String description;
   
   EnumSessionConstants(String description){
       
       this.description=description;
   }

    public String getDescription()
    {
        return description;
    }
    
    public void setDescription( String description )
    {
        this.description = description;
    }

   
}
