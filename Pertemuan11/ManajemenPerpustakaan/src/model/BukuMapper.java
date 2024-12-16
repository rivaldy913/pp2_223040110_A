/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
import org.apache.ibatis.annotations.*;
import java.util.List;

public interface BukuMapper {

    // Mendapatkan semua data buku
    @Select("SELECT * FROM books")
    List<Buku> getAllBooks();

    // Menambahkan data buku baru
    @Insert("INSERT INTO books (title, author, publisher, pages, stock) VALUES (#{title}, #{author}, #{publisher}, #{pages}, #{stock})")
    void insertBook(Buku buku);

    // Memperbarui data buku berdasarkan ID
    @Update("UPDATE books SET title = #{title}, author = #{author}, publisher = #{publisher}, pages = #{pages}, stock = #{stock} WHERE id = #{id}")
    void updateBook(Buku buku);

    // Menghapus data buku berdasarkan ID
    @Delete("DELETE FROM books WHERE id = #{id}")
    void deleteBookById(int id);
}
