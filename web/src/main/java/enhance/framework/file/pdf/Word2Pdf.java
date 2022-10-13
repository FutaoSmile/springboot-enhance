package enhance.framework.file.pdf;

import com.futao.springboot.enhance.framework.api.exception.ApplicationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.docx4j.Docx4J;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFont;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * https://gitee.com/rox/docx4j-sample#https://gitee.com/link?target=https%3A%2F%2Fwww.docx4java.org%2Fforums%2Fdocx-java-f6%2Finline-vs-anchor-images-t726.html
 * Word文件转PDF文件
 *
 * @author futaosmile@gmail.com
 * @since 2022/9/1
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Word2Pdf {

    private static final String SIM_SUN = "SimSun";
    public static final String TIMES_NEW_ROMAN = "Times New Roman";

    /**
     * Word文件转PDF文件
     *
     * @param wordFileInputStream word文件输入流
     * @param outputStream        pdf文件输出流
     * @author futaosmile@gmail.com
     * @since 2022/9/1 10:02
     */
    public static void word2Pdf(InputStream wordFileInputStream, OutputStream outputStream) {
        try {
            WordprocessingMLPackage mlPackage = WordprocessingMLPackage.load(wordFileInputStream);
            setFontMapper(mlPackage);
            Docx4J.toPDF(mlPackage, outputStream);
        } catch (Docx4JException e) {
            throw ApplicationException.ae("word转pdf，发生异常", e);
        } catch (Exception e) {
            throw ApplicationException.ae("word转pdf，字体设置异常", e);
        }
    }


    private static void setFontMapper(WordprocessingMLPackage mlPackage) throws Exception {
        Mapper fontMapper = new IdentityPlusMapper();
        PhysicalFonts.addPhysicalFonts("FangSong", new URL("classpath:fonts/simfang.ttf"));
        PhysicalFonts.addPhysicalFonts("仿宋", new URL("classpath:fonts/simfang.ttf"));

        PhysicalFonts.addPhysicalFonts(TIMES_NEW_ROMAN, new URL("classpath:fonts/times.ttf"));
        PhysicalFonts.addPhysicalFonts("Times New Roman,normal,400", new URL("classpath:fonts/times.ttf"));
        PhysicalFonts.addPhysicalFonts("楷体", new URL("classpath:fonts/simkai.ttf"));
        PhysicalFonts.addPhysicalFonts("KaiTi", new URL("classpath:fonts/simkai.ttf"));

        //加载字体文件（解决无中文字体问题）
        fontMapper.put("隶书", getPhysicalFont("LiSu"));
        fontMapper.put("宋体", getPhysicalFont(SIM_SUN));
        fontMapper.put("微软雅黑", getPhysicalFont("Microsoft Yahei"));
        fontMapper.put("黑体", getPhysicalFont("SimHei"));
        fontMapper.put("楷体", getPhysicalFont("KaiTi"));
        fontMapper.put("新宋体", getPhysicalFont("NSimSun"));
        fontMapper.put("华文行楷", getPhysicalFont("STXingkai"));
        fontMapper.put("华文仿宋", getPhysicalFont("STFangsong"));
        fontMapper.put("仿宋", getPhysicalFont("FangSong"));
        fontMapper.put("幼圆", getPhysicalFont("YouYuan"));
        fontMapper.put("华文宋体", getPhysicalFont("STSong"));
        fontMapper.put("华文中宋", getPhysicalFont("STZhongsong"));
        fontMapper.put("等线", getPhysicalFont(SIM_SUN));
        fontMapper.put("等线 Light", getPhysicalFont(SIM_SUN));
        fontMapper.put("华文琥珀", getPhysicalFont("STHupo"));
        fontMapper.put("华文隶书", getPhysicalFont("STLiti"));
        fontMapper.put("华文新魏", getPhysicalFont("STXinwei"));
        fontMapper.put("华文彩云", getPhysicalFont("STCaiyun"));
        fontMapper.put("方正姚体", getPhysicalFont("FZYaoti"));
        fontMapper.put("方正舒体", getPhysicalFont("FZShuTi"));
        fontMapper.put("华文细黑", getPhysicalFont("STXihei"));
        fontMapper.put("宋体扩展", getPhysicalFont("simsun-extB"));
        fontMapper.put("仿宋_GB2312", getPhysicalFont("FangSong_GB2312"));
        fontMapper.put("新細明體", getPhysicalFont(SIM_SUN));
        fontMapper.put(TIMES_NEW_ROMAN, getPhysicalFont(TIMES_NEW_ROMAN));
        fontMapper.put("Times New Roman,normal,400", getPhysicalFont(TIMES_NEW_ROMAN));
        //解决宋体（正文）和宋体（标题）的乱码问题
        PhysicalFonts.put("PMingLiU", getPhysicalFont(SIM_SUN));
        PhysicalFonts.put("新細明體", getPhysicalFont(SIM_SUN));
        //宋体&新宋体
        fontMapper.put(SIM_SUN, getPhysicalFont(SIM_SUN));
        //设置字体
        mlPackage.setFontMapper(fontMapper);
    }

    private static PhysicalFont getPhysicalFont(String fontName) {
        PhysicalFont physicalFont = PhysicalFonts.get(fontName);
        log.info("fontParam:{},res:{}", fontName, physicalFont);
        return physicalFont;
    }

}
