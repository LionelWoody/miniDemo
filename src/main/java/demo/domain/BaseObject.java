package demo.domain;

import java.io.Serializable;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;

/**
 * Created by <a href="mailto:javaworld@qq.com">Woody</a>
 */
public abstract class BaseObject implements Serializable {

    private static final long serialVersionUID = -2972657279105071776L;

    /**
     * Returns a multi-line String with key=value pairs.
     *
     * @return a String representation of this class.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append( this.getClass().getName() );
        result.append( " => {" );
        result.append(newLine);

        Field[] fields = this.getClass().getDeclaredFields();

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        for ( Field field : fields  ) {
            result.append(" ");
            try {
                field.setAccessible(true);
                result.append( field.getName() );
                result.append(": ");
                MethodHandle mh = lookup.unreflectGetter(field);
                result.append( mh.invoke(this) );
            } catch (Throwable throwable) {
                System.out.println(throwable.getMessage());
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    }

    /**
     * Compares object equality. When using Hibernate, the primary key should
     * not be a part of this comparison.
     *
     * @param o object to compare to
     * @return true/false based on equality tests
     */
    public abstract boolean equals(final Object o);

    /**
     * When you override equals, you should override hashCode. See "Why are
     * equals() and hashCode() importation" for more information:
     * http://www.hibernate.org/109.html
     *
     * @return hashCode
     */
    public abstract int hashCode();

    /**
     * 引入包最少原则，把 commons-lang3 去掉了
     */ /*
    class CustomToStringStyle extends ToStringStyle {
        private static final long serialVersionUID = 1L;

        protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
            if (value instanceof Date) {
                value = new SimpleDateFormat("yyyy-MM-dd").format(value);
            }
            buffer.append(value);
        }
    }
    public String toString() {
        return ToStringBuilder.reflectionToString(this, new CustomToStringStyle());
    }
*/
}
