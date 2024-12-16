/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author ASUS
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.List;
import model.*;
import org.apache.ibatis.session.SqlSession;
import view.BukuView;

public class BukuController {
    private BukuView view;
    private BukuMapper mapper;

    public BukuController(BukuView view, BukuMapper mapper) {
        this.view = view;
        this.mapper = mapper;

        this.view.addAddBookListener(new AddBookListener());
        this.view.addRefreshListener(new RefreshListener());
        this.view.addDeleteBookListener(new DeleteBookListener());
        this.view.addUpdateBookListener(new UpdateBookListener());
    }

    class AddBookListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = view.getTitleInput();
            String author = view.getAuthorInput();
            String publisher = view.getPublisherInput();
            String pages = view.getPagesInput();
            String stock = view.getStockInput();

            if (title.isEmpty() || author.isEmpty() || publisher.isEmpty() || pages.isEmpty() || stock.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please fill in all fields.");
                return;
            }

            try (SqlSession session = MyBatisUtil.getSqlSession()) {
                BukuMapper mapper = session.getMapper(BukuMapper.class);
                
                int pagesInt = Integer.parseInt(pages);
                int stockInt = Integer.parseInt(stock);
                
                Buku book = new Buku();
                book.setTitle(title);
                book.setAuthor(author);
                book.setPublisher(publisher);
                book.setPages(pagesInt);
                book.setStock(stockInt);

                mapper.insertBook(book);
                session.commit();
                JOptionPane.showMessageDialog(view, "Book added successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Please enter valid numbers for pages and stock.");
            }
        }
    }

    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setBookList(new String[0][0]);

            List<Buku> books = mapper.getAllBooks();

            String[][] bookArray = books.stream()
                .map(book -> new String[] {
                    String.valueOf(book.getId()),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getPublisher(),
                    String.valueOf(book.getPages()),
                    String.valueOf(book.getStock())
                })
                .toArray(String[][]::new);

            view.setBookList(bookArray);
        }
    }

    class DeleteBookListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedId = view.getSelectedBookId();
            if (selectedId != null) {
                try (SqlSession session = MyBatisUtil.getSqlSession()) {
                    BukuMapper mapper = session.getMapper(BukuMapper.class);
                    mapper.deleteBookById(Integer.parseInt(selectedId));
                    session.commit();
                    JOptionPane.showMessageDialog(view, "Book deleted successfully!");
                }
            } else {
                JOptionPane.showMessageDialog(view, "Please select a book to delete.");
            }
        }
    }

    class UpdateBookListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedId = view.getSelectedBookId();
            if (selectedId != null) {
                String title = view.getTitleInput();
                String author = view.getAuthorInput();
                String publisher = view.getPublisherInput();
                String pages = view.getPagesInput();
                String stock = view.getStockInput();

                if (title.isEmpty() || author.isEmpty() || publisher.isEmpty() || pages.isEmpty() || stock.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Please fill in all fields.");
                    return;
                }

                try (SqlSession session = MyBatisUtil.getSqlSession()) {
                    BukuMapper mapper = session.getMapper(BukuMapper.class);
                    
                    int pagesInt = Integer.parseInt(pages);
                    int stockInt = Integer.parseInt(stock);

                    Buku book = new Buku();
                    book.setId(Integer.parseInt(selectedId));
                    book.setTitle(title);
                    book.setAuthor(author);
                    book.setPublisher(publisher);
                    book.setPages(pagesInt);
                    book.setStock(stockInt);

                    mapper.updateBook(book);
                    session.commit();
                    JOptionPane.showMessageDialog(view, "Book updated successfully!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(view, "Please enter valid numbers for pages and stock.");
                }
            } else {
                JOptionPane.showMessageDialog(view, "Please select a book to update.");
            }
        }
    }
}
