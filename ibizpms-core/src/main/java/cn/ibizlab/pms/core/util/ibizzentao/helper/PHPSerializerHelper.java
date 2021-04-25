package cn.ibizlab.pms.core.util.ibizzentao.helper;


import org.phprpc.util.AssocArray;
import org.phprpc.util.Cast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * @author huwei
 */
public class PHPSerializerHelper {
    private static final HashMap clscache = new HashMap();
    private static final HashMap fieldcache = new HashMap();
    private static final HashMap __sleepcache = new HashMap();
    private static final HashMap __wakeupcache = new HashMap();
    private static final byte __Quote = 34;
    private static final byte __0 = 48;
    private static final byte __1 = 49;
    private static final byte __Colon = 58;
    private static final byte __Semicolon = 59;
    private static final byte __C = 67;
    private static final byte __N = 78;
    private static final byte __O = 79;
    private static final byte __R = 82;
    private static final byte __S = 83;
    private static final byte __U = 85;
    private static final byte __Slash = 92;
    private static final byte __a = 97;
    private static final byte __b = 98;
    private static final byte __d = 100;
    private static final byte __i = 105;
    private static final byte __r = 114;
    private static final byte __s = 115;
    private static final byte __LeftB = 123;
    private static final byte __RightB = 125;
    private static final String __NAN = "NAN";
    private static final String __INF = "INF";
    private static final String __NINF = "-INF";
    private String charset = "UTF-8";
    private static Class enumClass;
    private static Field enumOrdinal;

    public PHPSerializerHelper() {
    }

    public String getCharset() {
        return this.charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public byte[] serialize(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        HashMap ht = new HashMap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        this.serialize(stream, obj, ht, 1);
        byte[] result = stream.toByteArray();
        return result;
    }

    private int serialize(ByteArrayOutputStream stream, Object obj, HashMap ht, int hv) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (obj == null) {
            ++hv;
            this.writeNull(stream);
        } else if (obj instanceof Boolean) {
            ++hv;
            this.writeBoolean(stream, (byte)((Boolean)obj ? 49 : 48));
        } else if (!(obj instanceof Byte) && !(obj instanceof Short) && !(obj instanceof Integer)) {
            if (obj instanceof Long) {
                ++hv;
                this.writeDouble(stream, this.getAsciiBytes(obj));
            } else if (obj instanceof Float) {
                ++hv;
                Float f = (Float)obj;
                obj = f.isNaN() ? "NAN" : (!f.isInfinite() ? obj : (f > 0.0F ? "INF" : "-INF"));
                this.writeDouble(stream, this.getAsciiBytes(obj));
            } else if (obj instanceof Double) {
                ++hv;
                Double d = (Double)obj;
                obj = d.isNaN() ? "NAN" : (!d.isInfinite() ? obj : (d > 0.0D ? "INF" : "-INF"));
                this.writeDouble(stream, this.getAsciiBytes(obj));
            } else if (obj instanceof byte[]) {
                if (ht.containsKey(obj)) {
                    this.writeRef(stream, this.getAsciiBytes(ht.get(obj)));
                } else {
                    ht.put(obj, new Integer(hv));
                    this.writeString(stream, (byte[])((byte[])obj));
                }

                ++hv;
            } else if (obj instanceof char[]) {
                if (ht.containsKey(obj)) {
                    this.writeRef(stream, this.getAsciiBytes(ht.get(obj)));
                } else {
                    ht.put(obj, new Integer(hv));
                    this.writeString(stream, this.getBytes(new String((char[])((char[])obj))));
                }

                ++hv;
            } else if (!(obj instanceof Character) && !(obj instanceof String) && !(obj instanceof StringBuffer)) {
                if (!(obj instanceof BigInteger) && !(obj instanceof BigDecimal) && !(obj instanceof Number)) {
                    if (obj instanceof Date) {
                        if (ht.containsKey(obj)) {
                            ++hv;
                            this.writeRef(stream, this.getAsciiBytes(ht.get(obj)));
                        } else {
                            ht.put(obj, new Integer(hv));
                            hv += 8;
                            this.writeDate(stream, (Date)obj);
                        }
                    } else if (obj instanceof Calendar) {
                        if (ht.containsKey(obj)) {
                            ++hv;
                            this.writeRef(stream, this.getAsciiBytes(ht.get(obj)));
                        } else {
                            ht.put(obj, new Integer(hv));
                            hv += 8;
                            this.writeCalendar(stream, (Calendar)obj);
                        }
                    } else if (!(obj instanceof Serializable)) {
                        this.writeNull(stream);
                    } else if (obj instanceof AssocArray) {
                        obj = ((AssocArray)obj).toHashMap();
                        if (ht.containsKey(obj)) {
                            this.writePointRef(stream, this.getAsciiBytes(ht.get(obj)));
                        } else {
                            ht.put(obj, new Integer(hv++));
                            hv = this.writeMap(stream, (Map)obj, ht, hv);
                        }
                    } else if (obj.getClass().isArray()) {
                        if (ht.containsKey(obj)) {
                            this.writePointRef(stream, this.getAsciiBytes(ht.get(obj)));
                        } else {
                            ht.put(obj, new Integer(hv++));
                            hv = this.writeArray(stream, obj, ht, hv);
                        }
                    } else if (obj instanceof List) {
                        if (ht.containsKey(obj)) {
                            this.writePointRef(stream, this.getAsciiBytes(ht.get(obj)));
                        } else {
                            ht.put(obj, new Integer(hv++));
                            hv = this.writeList(stream, (List)obj, ht, hv);
                        }
                    } else if (obj instanceof Collection) {
                        if (ht.containsKey(obj)) {
                            this.writePointRef(stream, this.getAsciiBytes(ht.get(obj)));
                        } else {
                            ht.put(obj, new Integer(hv++));
                            hv = this.writeCollection(stream, (Collection)obj, ht, hv);
                        }
                    } else if (obj instanceof Map) {

                        ht.put(obj, new Integer(hv++));
                        hv = this.writeMap(stream, (Map)obj, ht, hv);

                    } else if (enumClass != null && enumClass.isAssignableFrom(obj.getClass())) {
                        ++hv;
                        this.writeInteger(stream, this.getAsciiBytes(enumOrdinal.get(obj)));
                    } else if (ht.containsKey(obj)) {
                        ++hv;
                        this.writeRef(stream, this.getAsciiBytes(ht.get(obj)));
                    } else {
                        ht.put(obj, new Integer(hv++));
                        hv = this.writeObject(stream, obj, ht, hv);
                    }
                } else {

                    ht.put(obj, new Integer(hv));
                    this.writeString(stream, this.getAsciiBytes(obj));


                    ++hv;
                }
            } else {

                ht.put(obj, new Integer(hv));
                this.writeString(stream, this.getBytes(obj));

                ++hv;
            }
        } else {
            ++hv;
            this.writeInteger(stream, this.getAsciiBytes(obj));
        }

        return hv;
    }

    private void writeNull(ByteArrayOutputStream stream) {
        stream.write(78);
        stream.write(59);
    }

    private void writeRef(ByteArrayOutputStream stream, byte[] r) {
        stream.write(114);
        stream.write(58);
        stream.write(r, 0, r.length);
        stream.write(59);
    }

    private void writePointRef(ByteArrayOutputStream stream, byte[] p) {
        stream.write(82);
        stream.write(58);
        stream.write(p, 0, p.length);
        stream.write(59);
    }

    private void writeBoolean(ByteArrayOutputStream stream, byte b) {
        stream.write(98);
        stream.write(58);
        stream.write(b);
        stream.write(59);
    }

    private void writeInteger(ByteArrayOutputStream stream, byte[] i) {
        stream.write(105);
        stream.write(58);
        stream.write(i, 0, i.length);
        stream.write(59);
    }

    private void writeDouble(ByteArrayOutputStream stream, byte[] d) {
        stream.write(100);
        stream.write(58);
        stream.write(d, 0, d.length);
        stream.write(59);
    }

    private void writeString(ByteArrayOutputStream stream, byte[] s) {
        byte[] slen = this.getAsciiBytes(new Integer(s.length));
        stream.write(115);
        stream.write(58);
        stream.write(slen, 0, slen.length);
        stream.write(58);
        stream.write(34);
        stream.write(s, 0, s.length);
        stream.write(34);
        stream.write(59);
    }

    private void writeCalendar(ByteArrayOutputStream stream, Calendar calendar) {
        byte[] typeName = this.getBytes("PHPRPC_Date");
        byte[] classNameLen = this.getAsciiBytes(new Integer(typeName.length));
        stream.write(79);
        stream.write(58);
        stream.write(classNameLen, 0, classNameLen.length);
        stream.write(58);
        stream.write(34);
        stream.write(typeName, 0, typeName.length);
        stream.write(34);
        stream.write(58);
        stream.write(55);
        stream.write(58);
        stream.write(123);
        this.writeString(stream, this.getBytes("year"));
        this.writeInteger(stream, this.getAsciiBytes(new Integer(calendar.get(1))));
        this.writeString(stream, this.getBytes("month"));
        this.writeInteger(stream, this.getAsciiBytes(new Integer(calendar.get(2) + 1)));
        this.writeString(stream, this.getBytes("day"));
        this.writeInteger(stream, this.getAsciiBytes(new Integer(calendar.get(5))));
        this.writeString(stream, this.getBytes("hour"));
        this.writeInteger(stream, this.getAsciiBytes(new Integer(calendar.get(11))));
        this.writeString(stream, this.getBytes("minute"));
        this.writeInteger(stream, this.getAsciiBytes(new Integer(calendar.get(12))));
        this.writeString(stream, this.getBytes("second"));
        this.writeInteger(stream, this.getAsciiBytes(new Integer(calendar.get(13))));
        this.writeString(stream, this.getBytes("millisecond"));
        this.writeInteger(stream, this.getAsciiBytes(new Integer(0)));
        stream.write(125);
    }

    private void writeDate(ByteArrayOutputStream stream, Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        this.writeCalendar(stream, calendar);
    }

    private int writeArray(ByteArrayOutputStream stream, Object a, HashMap ht, int hv) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        int len = Array.getLength(a);
        byte[] alen = this.getAsciiBytes(new Integer(len));
        stream.write(97);
        stream.write(58);
        stream.write(alen, 0, alen.length);
        stream.write(58);
        stream.write(123);

        for(int i = 0; i < len; ++i) {
            this.writeInteger(stream, this.getAsciiBytes(new Integer(i)));
            hv = this.serialize(stream, Array.get(a, i), ht, hv);
        }

        stream.write(125);
        return hv;
    }

    private int writeCollection(ByteArrayOutputStream stream, Collection c, HashMap ht, int hv) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        int len = c.size();
        byte[] alen = this.getAsciiBytes(new Integer(len));
        stream.write(97);
        stream.write(58);
        stream.write(alen, 0, alen.length);
        stream.write(58);
        stream.write(123);
        int i = 0;

        Object value;
        for(Iterator values = c.iterator(); values.hasNext(); hv = this.serialize(stream, value, ht, hv)) {
            this.writeInteger(stream, this.getAsciiBytes(new Integer(i++)));
            value = values.next();
        }

        stream.write(125);
        return hv;
    }

    private int writeList(ByteArrayOutputStream stream, List a, HashMap ht, int hv) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        int len = a.size();
        byte[] alen = this.getAsciiBytes(new Integer(len));
        stream.write(97);
        stream.write(58);
        stream.write(alen, 0, alen.length);
        stream.write(58);
        stream.write(123);

        for(int i = 0; i < len; ++i) {
            this.writeInteger(stream, this.getAsciiBytes(new Integer(i)));
            hv = this.serialize(stream, a.get(i), ht, hv);
        }

        stream.write(125);
        return hv;
    }

    private int writeMap(ByteArrayOutputStream stream, Map h, HashMap ht, int hv) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        int len = h.size();
        byte[] hlen = this.getAsciiBytes(new Integer(len));
        stream.write(97);
        stream.write(58);
        stream.write(hlen, 0, hlen.length);
        stream.write(58);
        stream.write(123);

        Object key;
        for(Iterator keys = h.keySet().iterator(); keys.hasNext(); hv = this.serialize(stream, h.get(key), ht, hv)) {
            key = keys.next();
            if (!(key instanceof Byte) && !(key instanceof Short) && !(key instanceof Integer)) {
                if (key instanceof Boolean) {
                    this.writeInteger(stream, new byte[]{(byte)((Boolean)key ? 49 : 48)});
                } else {
                    this.writeString(stream, this.getBytes(key));
                }
            } else {
                this.writeInteger(stream, this.getAsciiBytes(key));
            }
        }

        stream.write(125);
        return hv;
    }

    private int writeObject(ByteArrayOutputStream stream, Object obj, HashMap ht, int hv) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class cls = obj.getClass();
        byte[] className = this.getBytes(getClassName(cls));
        byte[] classNameLen = this.getAsciiBytes(new Integer(className.length));
        if (obj instanceof org.phprpc.util.Serializable) {
            byte[] cs = ((org.phprpc.util.Serializable)obj).serialize();
            byte[] cslen = this.getAsciiBytes(new Integer(cs.length));
            stream.write(67);
            stream.write(58);
            stream.write(classNameLen, 0, classNameLen.length);
            stream.write(58);
            stream.write(34);
            stream.write(className, 0, className.length);
            stream.write(34);
            stream.write(58);
            stream.write(cslen, 0, cslen.length);
            stream.write(58);
            stream.write(123);
            stream.write(cs, 0, cs.length);
            stream.write(125);
        } else {
            Method __sleep = null;
            HashMap f;
            if (fieldcache.containsKey(cls)) {
                f = (HashMap)fieldcache.get(cls);
                if (__sleepcache.containsKey(cls)) {
                    __sleep = (Method)__sleepcache.get(cls);
                    __sleep.invoke(obj);
                }
            } else {
                try {
                    __sleep = cls.getMethod("__sleep");
                    __sleep.setAccessible(true);
                    __sleepcache.put(cls, __sleep);
                } catch (Exception var14) {
                }

                if (__sleep != null) {
                    String[] fieldNames = (String[])((String[])__sleep.invoke(obj));
                    f = this.getFields(obj, fieldNames);
                } else {
                    f = this.getFields(obj);
                }

                fieldcache.put(cls, f);
            }

            byte[] flen = this.getAsciiBytes(new Integer(f.size()));
            stream.write(79);
            stream.write(58);
            stream.write(classNameLen, 0, classNameLen.length);
            stream.write(58);
            stream.write(34);
            stream.write(className, 0, className.length);
            stream.write(34);
            stream.write(58);
            stream.write(flen, 0, flen.length);
            stream.write(58);
            stream.write(123);

            Object o;
            for(Iterator keys = f.keySet().iterator(); keys.hasNext(); hv = this.serialize(stream, o, ht, hv)) {
                String key = (String)keys.next();
                o = ((Field)f.get(key)).get(obj);
                this.writeString(stream, this.getBytes(key));
            }

            stream.write(125);
        }

        return hv;
    }

    private byte[] getBytes(Object obj) {
        try {
            return obj.toString().getBytes(this.charset);
        } catch (Exception var3) {
            return obj.toString().getBytes();
        }
    }

    private byte[] getAsciiBytes(Object obj) {
        try {
            return obj.toString().getBytes("US-ASCII");
        } catch (Exception var3) {
            return null;
        }
    }

    private String getString(byte[] b) {
        try {
            return new String(b, this.charset);
        } catch (Exception var3) {
            return new String(b);
        }
    }

    private Class getInnerClass(StringBuffer className, int[] pos, int i, char c) {
        if (i < pos.length) {
            int p = pos[i];
            className.setCharAt(p, c);
            Class cls = this.getInnerClass(className, pos, i + 1, '_');
            if (i + 1 < pos.length && cls == null) {
                cls = this.getInnerClass(className, pos, i + 1, '$');
            }

            return cls;
        } else {
            try {
                return Class.forName(className.toString());
            } catch (Exception var7) {
                return null;
            }
        }
    }

    private Class getClass(StringBuffer className, int[] pos, int i, char c) {
        if (i < pos.length) {
            int p = pos[i];
            className.setCharAt(p, c);
            Class cls = this.getClass(className, pos, i + 1, '.');
            if (i + 1 < pos.length) {
                if (cls == null) {
                    cls = this.getClass(className, pos, i + 1, '_');
                }

                if (cls == null) {
                    cls = this.getInnerClass(className, pos, i + 1, '$');
                }
            }

            return cls;
        } else {
            try {
                return Class.forName(className.toString());
            } catch (Exception var7) {
                return null;
            }
        }
    }

    public Class getClass(String className) {
        if (clscache.containsKey(className)) {
            return (Class)clscache.get(className);
        } else {
            StringBuffer cn = new StringBuffer(className);
            ArrayList al = new ArrayList();

            for(int p = cn.indexOf("_"); p > -1; p = cn.indexOf("_", p + 1)) {
                al.add(new Integer(p));
            }

            Class cls = null;
            if (al.size() > 0) {
                try {
                    int[] pos = (int[])((int[]) Cast.toArray(al, Integer.TYPE, this.charset));
                    cls = this.getClass(cn, pos, 0, '.');
                    if (cls == null) {
                        cls = this.getClass(cn, pos, 0, '_');
                    }

                    if (cls == null) {
                        cls = this.getInnerClass(cn, pos, 0, '$');
                    }
                } catch (Exception var8) {
                }
            } else {
                try {
                    cls = Class.forName(className.toString());
                } catch (Exception var7) {
                }
            }

            clscache.put(className, cls);
            return cls;
        }
    }

    public static String getClassName(Class cls) {
        String className = cls.getName().replace('.', '_').replace('$', '_');
        if (!clscache.containsKey(className)) {
            clscache.put(className, cls);
        }

        return className;
    }

    public static Field getField(Object obj, String fieldName) {
        Class cls = obj.getClass();

        while(cls != null) {
            try {
                Field field = cls.getDeclaredField(fieldName);
                int mod = field.getModifiers();
                if (!Modifier.isTransient(mod) && !Modifier.isStatic(mod)) {
                    field.setAccessible(true);
                    return field;
                }

                return null;
            } catch (Exception var5) {
                cls = cls.getSuperclass();
            }
        }

        return null;
    }

    private HashMap getFields(Object obj, String[] fieldNames) {
        if (fieldNames == null) {
            return this.getFields(obj);
        } else {
            int n = fieldNames.length;
            HashMap fields = new HashMap(n);

            for(int i = 0; i < n; ++i) {
                Field f = getField(obj, fieldNames[i]);
                if (f != null) {
                    fields.put(fieldNames[i], f);
                }
            }

            return fields;
        }
    }

    private HashMap getFields(Object obj) {
        HashMap fields = new HashMap();

        for(Class cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            Field[] fs = cls.getDeclaredFields();

            for(int i = 0; i < fs.length; ++i) {
                Field field = fs[i];
                int mod = fs[i].getModifiers();
                if (!Modifier.isTransient(mod) && !Modifier.isStatic(mod)) {
                    field.setAccessible(true);
                    if (fields.get(field.getName()) == null) {
                        fields.put(field.getName(), field);
                    }
                }
            }
        }

        return fields;
    }

    public static Object newInstance(Class cls) {
        return newInstance(cls, true);
    }

    private static Object newInstance(Class cls, boolean tryagain) {
        try {
            if (tryagain) {
                return cls.newInstance();
            } else {
                ObjectStreamClass desc = ObjectStreamClass.lookup(cls);
                Method m = ObjectStreamClass.class.getDeclaredMethod("newInstance");
                m.setAccessible(true);
                return m.invoke(desc);
            }
        } catch (Exception var4) {
            return tryagain ? newInstance(cls, false) : null;
        }
    }

    public Object unserialize(byte[] ss) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        return this.unserialize(ss, Object.class);
    }

    public Object unserialize(byte[] ss, Class cls) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ByteArrayInputStream stream = new ByteArrayInputStream(ss);
        Object result = this.unserialize(stream, new ArrayList());
        return Cast.cast(result, cls, this.charset);
    }

    private Object unserialize(ByteArrayInputStream stream, ArrayList objectContainer) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        switch(stream.read()) {
            case 67:
                return this.readCustomObject(stream, objectContainer);
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 80:
            case 81:
            case 84:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
            case 99:
            case 101:
            case 102:
            case 103:
            case 104:
            case 106:
            case 107:
            case 108:
            case 109:
            case 110:
            case 111:
            case 112:
            case 113:
            default:
                return null;
            case 78:
                Object obj = this.readNull(stream);
                objectContainer.add(obj);
                return obj;
            case 79:
                return this.readObject(stream, objectContainer);
            case 82:
                return this.readPointRef(stream, objectContainer);
            case 83:
                obj = this.readEscapedString(stream);
                objectContainer.add(obj);
                return obj;
            case 85:
                obj = this.readUnicodeString(stream);
                objectContainer.add(obj);
                return obj;
            case 97:
                return this.readAssocArray(stream, objectContainer);
            case 98:
                obj = this.readBoolean(stream);
                objectContainer.add(obj);
                return obj;
            case 100:
                obj = this.readDouble(stream);
                objectContainer.add(obj);
                return obj;
            case 105:
                obj = this.readInteger(stream);
                objectContainer.add(obj);
                return obj;
            case 114:
                return this.readRef(stream, objectContainer);
            case 115:
                obj = this.readString(stream);
                objectContainer.add(obj);
                return obj;
        }
    }

    private String readNumber(ByteArrayInputStream stream) {
        StringBuffer sb = new StringBuffer();

        for(int i = stream.read(); i != 59 && i != 58; i = stream.read()) {
            sb.append((char)i);
        }

        return sb.toString();
    }

    private Object readNull(ByteArrayInputStream stream) {
        stream.skip(1L);
        return null;
    }

    private Boolean readBoolean(ByteArrayInputStream stream) {
        stream.skip(1L);
        Boolean b = new Boolean(stream.read() == 49);
        stream.skip(1L);
        return b;
    }

    private Number readInteger(ByteArrayInputStream stream) {
        stream.skip(1L);
        return new Integer(this.readNumber(stream));
    }

    private Number readDouble(ByteArrayInputStream stream) {
        stream.skip(1L);
        String d = this.readNumber(stream);
        if ("NAN".equals(d)) {
            return new Double(0.0D / 0.0);
        } else if ("INF".equals(d)) {
            return new Double(1.0D / 0.0);
        } else if ("-INF".equals(d)) {
            return new Double(-1.0D / 0.0);
        } else if (d.indexOf(46) <= 0 && d.indexOf(101) <= 0 && d.indexOf(69) <= 0) {
            int len = d.length();
            char c = d.charAt(0);
            if (len < 19 || c == '-' && len < 20) {
                return new Long(d);
            } else if (len <= 20 && (c == '-' || len <= 19)) {
                try {
                    return new Long(d);
                } catch (Exception var6) {
                    return new Double(d);
                }
            } else {
                return new Double(d);
            }
        } else {
            return new Double(d);
        }
    }

    private byte[] readString(ByteArrayInputStream stream) {
        stream.skip(1L);
        int len = Integer.parseInt(this.readNumber(stream));
        stream.skip(1L);
        byte[] buf = new byte[len];
        stream.read(buf, 0, len);
        stream.skip(2L);
        return buf;
    }

    private byte[] readEscapedString(ByteArrayInputStream stream) {
        stream.skip(1L);
        int len = Integer.parseInt(this.readNumber(stream));
        stream.skip(1L);
        byte[] buf = new byte[len];

        for(int i = 0; i < len; ++i) {
            int c;
            if ((c = stream.read()) == 92) {
                char c1 = (char)stream.read();
                char c2 = (char)stream.read();
                buf[i] = (byte)(Integer.parseInt(new String(new char[]{c1, c2}), 16) & 255);
            } else {
                buf[i] = (byte)(c & 255);
            }
        }

        stream.skip(2L);
        return buf;
    }

    private String readUnicodeString(ByteArrayInputStream stream) {
        stream.skip(1L);
        int len = Integer.parseInt(this.readNumber(stream));
        stream.skip(1L);
        StringBuffer sb = new StringBuffer(len);

        for(int i = 0; i < len; ++i) {
            int c;
            if ((c = stream.read()) == 92) {
                char c1 = (char)stream.read();
                char c2 = (char)stream.read();
                char c3 = (char)stream.read();
                char c4 = (char)stream.read();
                sb.append((char)Integer.parseInt(new String(new char[]{c1, c2, c3, c4}), 16));
            } else {
                sb.append((char)c);
            }
        }

        stream.skip(2L);
        return sb.toString();
    }

    private Object readRef(ByteArrayInputStream stream, ArrayList objectContainer) {
        stream.skip(1L);
        Object obj = objectContainer.get(Integer.parseInt(this.readNumber(stream)) - 1);
        objectContainer.add(obj);
        return obj;
    }

    private Object readPointRef(ByteArrayInputStream stream, ArrayList objectContainer) {
        stream.skip(1L);
        return objectContainer.get(Integer.parseInt(this.readNumber(stream)) - 1);
    }

    private AssocArray readAssocArray(ByteArrayInputStream stream, ArrayList objectContainer) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        stream.skip(1L);
        int n = Integer.parseInt(this.readNumber(stream));
        stream.skip(1L);
        AssocArray a = new AssocArray(n);
        objectContainer.add(a);

        for(int i = 0; i < n; ++i) {
            Object key;
            switch(stream.read()) {
                case 83:
                    key = Cast.cast(this.readEscapedString(stream), String.class, this.charset);
                    break;
                case 85:
                    key = this.readUnicodeString(stream);
                    break;
                case 105:
                    key = new Integer(this.readInteger(stream).intValue());
                    break;
                case 115:
                    key = Cast.cast(this.readString(stream), String.class, this.charset);
                    break;
                default:
                    return null;
            }

            Object result = this.unserialize(stream, objectContainer);
            if (key instanceof Integer) {
                a.set((Integer)key, result);
            } else {
                a.set((String)key, result);
            }
        }

        stream.skip(1L);
        return a;
    }

    private Calendar readCalendar(ByteArrayInputStream stream, ArrayList objectContainer, int n) {
        HashMap dt = new HashMap(n);

        for(int i = 0; i < n; ++i) {
            String key;
            switch(stream.read()) {
                case 83:
                    key = this.getString(this.readEscapedString(stream));
                    break;
                case 85:
                    key = this.readUnicodeString(stream);
                    break;
                case 115:
                    key = this.getString(this.readString(stream));
                    break;
                default:
                    return null;
            }

            if (stream.read() != 105) {
                return null;
            }

            dt.put(key, Cast.cast(this.readInteger(stream), Integer.class));
        }

        stream.skip(1L);
        GregorianCalendar calendar = new GregorianCalendar((Integer)dt.get("year"), (Integer)dt.get("month") - 1, (Integer)dt.get("day"), (Integer)dt.get("hour"), (Integer)dt.get("minute"), (Integer)dt.get("second"));
        objectContainer.add(calendar);
        objectContainer.add(dt.get("year"));
        objectContainer.add(dt.get("month"));
        objectContainer.add(dt.get("day"));
        objectContainer.add(dt.get("hour"));
        objectContainer.add(dt.get("minute"));
        objectContainer.add(dt.get("second"));
        objectContainer.add(dt.get("millisecond"));
        return calendar;
    }

    private Object readObject(ByteArrayInputStream stream, ArrayList objectContainer) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        stream.skip(1L);
        int len = Integer.parseInt(this.readNumber(stream));
        stream.skip(1L);
        byte[] buf = new byte[len];
        stream.read(buf, 0, len);
        String cn = this.getString(buf);
        stream.skip(2L);
        int n = Integer.parseInt(this.readNumber(stream));
        stream.skip(1L);
        if ("PHPRPC_Date".equals(cn)) {
            return this.readCalendar(stream, objectContainer, n);
        } else {
            Class cls = this.getClass(cn);
            HashMap fields = null;
            Object o;
            if (cls != null) {
                if ((o = newInstance(cls)) == null) {
                    o = new HashMap(n);
                } else {
                    fields = (HashMap)fieldcache.get(cls);
                }
            } else {
                o = new HashMap(n);
            }

            objectContainer.add(o);

            for(int i = 0; i < n; ++i) {
                String key;
                switch(stream.read()) {
                    case 83:
                        key = this.getString(this.readEscapedString(stream));
                        break;
                    case 85:
                        key = this.readUnicodeString(stream);
                        break;
                    case 115:
                        key = this.getString(this.readString(stream));
                        break;
                    default:
                        return null;
                }

                if (key.charAt(0) == 0) {
                    key = key.substring(key.indexOf("\u0000", 1) + 1);
                }

                Object result = this.unserialize(stream, objectContainer);
                if (o instanceof HashMap) {
                    ((HashMap)o).put(key, result);
                } else {
                    Field f;
                    if (fields == null) {
                        f = getField(o, key);
                    } else {
                        f = (Field)fields.get(key);
                    }

                    if (f != null) {
                        f.set(o, Cast.cast(result, f.getType(), this.charset));
                    }
                }
            }

            stream.skip(1L);
            if (!(o instanceof HashMap)) {
                Method __wakeup = null;
                if (__wakeupcache.containsKey(cls)) {
                    __wakeup = (Method)__wakeupcache.get(cls);
                } else {
                    try {
                        __wakeup = cls.getMethod("__wakeup");
                        __wakeup.setAccessible(true);
                    } catch (Exception var14) {
                    }

                    __wakeupcache.put(cls, __wakeup);
                }

                if (__wakeup != null) {
                    __wakeup.invoke(o);
                }
            }

            return o;
        }
    }

    private Object readCustomObject(ByteArrayInputStream stream, ArrayList objectContainer) {
        stream.skip(1L);
        int len = Integer.parseInt(this.readNumber(stream));
        stream.skip(1L);
        byte[] buf = new byte[len];
        stream.read(buf, 0, len);
        String cn = this.getString(buf);
        stream.skip(2L);
        int n = Integer.parseInt(this.readNumber(stream));
        stream.skip(1L);
        Class cls = this.getClass(cn);
        Object o;
        if (cls != null) {
            o = newInstance(cls);
        } else {
            o = null;
        }

        objectContainer.add(o);
        if (o == null) {
            stream.skip((long)n);
        } else if (o instanceof org.phprpc.util.Serializable) {
            byte[] b = new byte[n];
            stream.read(b, 0, n);
            ((org.phprpc.util.Serializable)o).unserialize(b);
        } else {
            stream.skip((long)n);
        }

        stream.skip(1L);
        return o;
    }

    static {
        try {
            enumClass = Class.forName("java.lang.Enum");
            enumOrdinal = enumClass.getDeclaredField("ordinal");
            enumOrdinal.setAccessible(true);
        } catch (Exception var1) {
            enumClass = null;
            enumOrdinal = null;
        }

    }
}
