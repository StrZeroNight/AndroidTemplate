package com.zeronight.templet.common.utils.share;

/**
 * Created by Administrator on 2018/1/3.
 */

public class CustomerBean {


    private UserInfoBean userInfo;
    private String token;
    private String image_code;

    public String getImage_code() {
        return image_code;
    }

    public void setImage_code(String image_code) {
        this.image_code = image_code;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class UserInfoBean {
        private String id;
        private String user_name;
        private String phone;
        private String sex;
        private String age;
        private String avatar;
        private String area;
        private String type;
        private String parent_id;
        private String u_status;
        private String popularize_code;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getU_status() {
            return u_status;
        }

        public void setU_status(String u_status) {
            this.u_status = u_status;
        }

        public String getPopularize_code() {
            return popularize_code;
        }

        public void setPopularize_code(String popularize_code) {
            this.popularize_code = popularize_code;
        }
    }
}
