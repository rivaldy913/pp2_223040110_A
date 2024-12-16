/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
import controller.BukuController;
import model.BukuMapper;
import model.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import view.BukuView;

public class Main {
    public static void main(String[] args) {
        SqlSession session = MyBatisUtil.getSqlSession();
        BukuMapper mapper = session.getMapper(BukuMapper.class);

        BukuView view = new BukuView();
        new BukuController(view, mapper);

        view.setVisible(true);
    }
}
