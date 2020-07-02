package com.ecommerce.common.user.entity;

import com.ecommerce.common.base.BaseEntity;
import com.ecommerce.common.validationGroup.DeleteGroup;
import com.ecommerce.common.validationGroup.InsertGroup;
import com.ecommerce.common.validationGroup.UpdateGroup;
 /**
 * 检查类注解：
 * Edit by yousabla on 2020/7/02
 *
 * 1.空检查：
 * @Null 验证对象是否为null
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 *
 * @NotBlank 检查约束字符串是不是Null还有被Trim的长度是否大于0, 只对字符串, 且会去掉前后空格.
 *
 * @NotEmpty 检查约束元素是否为NULL或者是EMPTY.
 *
 * 2.布尔值检查：
 * @AssertTrue 验证 Boolean 对象是否为 true
 *
 * @AssertFalse 验证 Boolean 对象是否为 false
 *
 * 3.长度检查：
 * @Size(min=, max=) 验证对象（Array,Collection,Map,String）长度是否在给定的范围之内
 *
 * @Length(min=, max=) 验证字符串是否在给定长度范围之内
 *
 * 4.日期检查：
 * @Past 验证 Date 和 Calendar 对象是否在当前时间之前
 *
 * @Future 验证 Date 和 Calendar 对象是否在当前时间之后
 *
 * @Pattern 验证 String 对象是否符合正则表达式的规则
 *
 * 5.数值检查：
 * （建议使用在String,Integer类型，不建议使用在int类型上，因为表单值为“”时无法转换为int，但可以转换为String为"",Integer为null）
 *
 * @Min 验证 Number 和 String 对象是否大等于指定的值
 *
 * @Max 验证 Number 和 String 对象是否小等于指定的值
 *
 * @DecimalMax 被标注的值必须不大于约束中指定的最大值. 这个约束的参数是一个通过BigDecimal定义的最大值的字符串表示.小数存在精度
 *
 * @DecimalMin 被标注的值必须不小于约束中指定的最小值. 这个约束的参数是一个通过BigDecimal定义的最小值的字符串表示.小数存在精度
 *
 * @Digits 验证 Number 和 String 的构成是否合法
 *
 * @Digits(integer=,fraction=) 验证字符串是否是符合指定格式的数字，integer指定整数精度，fraction指定小数精度。
 *
 * @Range(min=, max=) 检查注解值是否在特定的最大值最小值之间（包含）。
 *
 * @Valid 递归的对关联对象进行校验, 如果关联对象是个集合或者数组,那么对其中的元素进行递归校验,如果是一个map,则对其中的值部分进行校验.(是否进行递归验证)
 *
 * @CreditCardNumber 信用卡验证
 *
 * @Email 验证是否是邮件地址，如果为null,不进行验证，算通过验证。
 *
 * @ScriptAssert(lang= , script=, alias=) 如果需要校验的业务逻辑比较复杂，简单的@NotBlank，@Min注解已经无法满足需求了，这时可以使用@ScriptAssert来指定进行校验的方法，通过方法来进行复杂业务逻辑的校验，然后返回 true或false来表明是否校验成功。
 *
 * @ScriptAssert(lang="javascript",script="com.learn.validate.domain.Student.checkParams(_this.name, _this.age, _this.classes)",groups=CHECK.class)
 *
 * @URL(protocol=,host=, port=,regexp=, flags=)
 */

import javax.validation.constraints.NotNull;

public class User implements BaseEntity<String> {
    @NotNull(message = "The userid shouldn't be null",groups = {InsertGroup.class, UpdateGroup.class, DeleteGroup.class})  //addUser  user_id shouldn't be null updateuser selectsuer deleteuser
    String user_id;
    @NotNull(message = "The username shouldn't be null",groups = {InsertGroup.class, UpdateGroup.class})
    String username;
    String password;
    String name;
    String rights;
    String role_id;
    String last_login;
    String ip;
    String status;
    String bz;
    String skin;
    String email;
    String number;
    String phone;
    int man_buyer_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getMan_buyer_id() {
        return man_buyer_id;
    }

    public void setMan_buyer_id(int man_buyer_id) {
        this.man_buyer_id = man_buyer_id;
    }

    @Override
    public String getId() {
        return this.user_id ;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", rights='" + rights + '\'' +
                ", role_id='" + role_id + '\'' +
                ", last_login='" + last_login + '\'' +
                ", ip='" + ip + '\'' +
                ", status='" + status + '\'' +
                ", bz='" + bz + '\'' +
                ", skin='" + skin + '\'' +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                ", phone='" + phone + '\'' +
                ", man_buyer_id=" + man_buyer_id +
                '}';
    }
}
