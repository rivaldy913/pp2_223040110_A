import model.MyBatisUtil;
import model.UserMapper;
import org.apache.ibatis.session.SqlSession;
import view.LibraryView;
import controller.UserController;

public class Main {
    public static void main(String[] args) {
        // Mendapatkan SqlSession dari MyBatisUtil
        SqlSession session = MyBatisUtil.getSqlSession();

        // Mendapatkan mapper dari session
        UserMapper mapper = session.getMapper(UserMapper.class);

        // Membuat instance dari UserView
        LibraryView view = new LibraryView();

        // Membuat controller dan menghubungkan View dengan Mapper
        new UserController(view, mapper);

        // Menampilkan View
        view.setVisible(true);
    }
}
