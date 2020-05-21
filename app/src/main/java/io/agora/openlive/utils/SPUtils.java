package io.agora.openlive.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.lang.reflect.Method;


/**  sp文件存取的封装处理
 * @Author feisher  on 2017年10月26日09:50:08  更新，简化代码结构
 * Email：458079442@qq.com
 */
public class SPUtils {
    //保存在手机里面的文件名
    public static final String FILE_NAME = "cache";
    public static SharedPreferences sp ;

    private static SharedPreferences init(Context context) {
        return sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public static <E>void put(Context context, @NonNull String key, @NonNull E value) {
        SharedPreferences.Editor editor = init(context).edit();
        if (value instanceof String||value instanceof Integer||value instanceof Boolean||
                value instanceof Float|| value instanceof Long||value instanceof Double) {
            editor.putString(key, String.valueOf(value));
        }else {
            editor.putString(key, new Gson().toJson(value));
        }
        SPCompat.apply(editor);
    }

    public static <E>E get(Context context, @NonNull String key, @NonNull E defaultValue) {
        String value = init(context).getString(key, String.valueOf(defaultValue));
        if (defaultValue instanceof String){
            return (E) value;
        }if (defaultValue instanceof Integer){
            return (E) Integer.valueOf(value);
        }if (defaultValue instanceof Boolean){
            return (E) Boolean.valueOf(value);
        }if (defaultValue instanceof Float){
            return (E) Float.valueOf(value);
        }if (defaultValue instanceof Long){
            return (E) Long.valueOf(value);
        }if (defaultValue instanceof Double){
            return (E) Double.valueOf(value);
        }
        //json为null的时候返回对象为null,gson已处理
        return (E) new Gson().fromJson(value,defaultValue.getClass());
    }

    public static void remove(Context context, String key) {
        SharedPreferences.Editor editor = init(context).edit();
        editor.remove(key);
        SPCompat.apply(editor);
    }

    public static void clear(Context context) {
        SharedPreferences.Editor editor = init(context).edit();
        editor.clear();
        SPCompat.apply(editor);
    }

    public static boolean contains(Context context, String key) {
        return init(context).contains(key);
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     * @author
     */
    private static class SPCompat {
        private static final Method S_APPLY_METHOD = findApplyMethod();

        /**
         * 反射查找apply的方法
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }
            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (S_APPLY_METHOD != null) {
                    S_APPLY_METHOD.invoke(editor);
                    return;
                }
            } catch (Exception e) {
            }
            editor.commit();
        }
    }
}