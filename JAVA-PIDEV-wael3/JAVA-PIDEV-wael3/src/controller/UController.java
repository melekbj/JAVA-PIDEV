/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controller;

import entity.User;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface UController<T> {
    
    void AddUser(T t);
    void DeleteUser(T t);
    void UpdateUser(T t);
    List<T> ReadAllUsers();
    T readById(int id);
}
