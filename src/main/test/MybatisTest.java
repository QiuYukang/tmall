import org.junit.Test;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Mybatis 逆向工程--不可随便运行
 */

public class MybatisTest {
//    @Test
//    public void testMbg() throws Exception {
//        List<String> warnings = new ArrayList<String>();
//        boolean overwrite = true;
//        InputStream is= MybatisTest.class.getClassLoader().getResource("generatorConfig.xml").openStream();
//        ConfigurationParser cp = new ConfigurationParser(warnings);
//        Configuration config = cp.parseConfiguration(is);
//        is.close();
//        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
//        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
//        myBatisGenerator.generate(null);
//    }
}
