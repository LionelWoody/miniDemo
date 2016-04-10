package demo.domain;

import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by <a href="mailto:javaworld@qq.com">Woody</a>
 */
@Entity
public class User extends BaseObject {

    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private long uid;// 自增长ID
    @Column(nullable = false, unique = true)
    private String phone;// 电话号码
    @Column(nullable = false, unique = true)
    private String nickname;// 用户名
    @Column(nullable = false)
    private boolean locked;// 是否被锁
    @Column(nullable = false)
    private String gender;     // 性别
    @Column
    private String email;// 电子邮件

    public User() {
    }

    public User(final String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return StringUtils.isEmpty(nickname) ?
                "(" + phone + ")" :
                nickname;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        final User user = (User) o;

        return !(this.phone != null ? !this.phone.equals(user.getPhone()) : user.getPhone() != null);

    }

    @Override
    public int hashCode() {
        return (phone != null ? phone.hashCode() : 0);
    }

    // getter & setter auto generated

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
