/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author M Zayan Hafizh H
 */
public class databaseController {
    ResultSet rs = null;
    PreparedStatement pst = null;
    Connection conn = (Connection) database.java_db();
    
    private void insertMhs(){
        
    }
}
