/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ablazebookstore.models;

/**
 *
 * @author ASUS 550
 */
public class Commentaire  {
   
    
    
   
    
    private int iduser;
    private String commentaire;

    
    
    public Commentaire () { 
        
    }
    
    public Commentaire (int iduser,String commentaire) { 
        this.iduser=iduser;
        this.commentaire=commentaire;
    }
    
    /**
     * @return the userid
     */
    public int getUserid() {
        return iduser;
    }

   
    public void setUserid(int iduser) {
        this.iduser = iduser;
    }

    /**
     * @return the commentaire
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * @param commentaire the commentaire to set
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
