import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class formatSQL<T> {

    private Object obj;//�洢����\

    private int  elementNum;//�洢�ֶθ���

    public StringBuilder  sql;//�洢sql

    private Map<String,List<String>>  mapSQL;//<element,<String,val>>

    private List<String> listSQL;//<element>

    private Class clzz;

    public formatSQL(T t) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //��ʼ��
        obj = t;
        sql = new StringBuilder();
        mapSQL = new HashMap<>();
        listSQL = new ArrayList<>();
        getNum();
        replaceSQL();
    }

    public formatSQL(){}

    private static String getMethodName(String fildeName){
                byte[] items = fildeName.getBytes();
                items[0] = (byte) ((char) items[0] - 'a' + 'A');
                return new String(items);
             }

    /**
     * ѭ����ȡ����ֵ
     */
    private  void   loopGet(Field[] fields) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        for(Field f:fields) tag:{
            //�����String����
            if(f.getGenericType().toString().equals("class java.lang.String")){
                List<String> li = new ArrayList<>();
                Method m = (Method) clzz.getMethod("get" + getMethodName(f.getName()));
                String val = (String) m.invoke(obj);
                if(val==null || val.equals("")){
                    break tag;
                }
                li.add("String");
                li.add(val);
                mapSQL.put(f.getName(),li);
                listSQL.add(f.getName());

            }

            //Integer
            if(f.getGenericType().toString().equals("class java.lang.Integer")){
                List<String> li = new ArrayList<>();
                Method m = (Method) clzz.getMethod("get" + getMethodName(f.getName()));
                Integer val = (Integer) m.invoke(obj);

                li.add("Integer");
                li.add(String.valueOf(val));
                mapSQL.put(f.getName(),li);
                listSQL.add(f.getName());

            }
        }

    }


    /**
     * ��ȡ�ֶθ���
     * ��ʼ��sql
     */
    public void  getNum() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        clzz = obj.getClass();
        Field[] fields = clzz.getDeclaredFields();
        loopGet(fields);
        elementNum = fields.length;
        sql.append("select * from tb where ");
    }
    /**
     * �滻sql
     */
    public  void  replaceSQL(){

        for(int i=0;i<elementNum;i++){

            if(mapSQL.get(listSQL.get(i)).get(1).equals("null")){
                break;
            }

            //�����String����
            if(mapSQL.get(listSQL.get(i)).get(0).equals("String")) {
                sql.append(listSQL.get(i) + "=\'" + mapSQL.get(listSQL.get(i)).get(1)+"\'  ");
            }

            //�����Integer����
            if(mapSQL.get(listSQL.get(i)).get(0).equals("Integer")) {
                sql.append(listSQL.get(i) + "=" + mapSQL.get(listSQL.get(i)).get(1)+"  ");
            }
        }

    }
}
