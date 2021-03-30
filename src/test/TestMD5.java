import alivestill.utils.MD5;

public class TestMD5 {
    public static MD5 md5 = new MD5();

    public static void main(String[] args) {
        System.out.println(md5.getMD5("凌海军"));
        System.out.println(md5.getMD5("alivestill"));
        System.out.println(md5.getMD5("AliveStill"));
        System.out.println(md5.getMD5("lingh"));
    }
}
