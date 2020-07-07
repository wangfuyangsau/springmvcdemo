package neu.work.seventh.utils;

/**
 * @author Fuyang Wang
 * @version V1.0
 * @Title: Factory
 * @Package neu.work.seventh.utils
 * @date 2020/4/25
 */
public class FactoryUtil {
    public static Object  getBean(String classPath){
        try {
            Class<?> clazz=Class.forName(classPath);
            return clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
