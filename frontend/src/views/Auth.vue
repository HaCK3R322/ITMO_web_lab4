<template>
    <div class="content">
        <form class="content__form">
            <div class="login">
            <label>Enter username
                <input id="loginTextinput" type="text" required placeholder="Login" v-model.trim="username"/>
            </label>
            </div>
            <div class="password">
            <label>Enter password
                <input id="passwordTextinput" type="password" required placeholder="Password" v-model.trim="password"/>
            </label>
            </div>
            <button id="logginButton" label="Login" @click="login">Login</button>
            <button id="registerButton" label="Register" @click="register">Register</button>
        </form>
    </div>
</template>

<script>
export default {
    name: "Auth",
    data() {
        return {
            username: "",
            password: ""
        };
    },

    methods: {
        login(event) {
            event.preventDefault();
            this.axios.post("http://localhost:8081/user/login", {
                username: this.username,
                password: this.password
            }).then(response => {
                localStorage.setItem("userDetails", JSON.stringify({userId: response.data.userId, accessToken: response.data.accessToken}));
                this.$router.push({name: 'app'})
            }).catch(error => {
                alert(error);
            })
        },
        register(event) {
            event.preventDefault();
            this.axios.post("http://localhost:8081/user/register", {
                username: this.username,
                password: this.password
            }).then(() => {
                alert("Successful");
            }).catch(error => {
                alert("Already registered!");
            })
        }
    }
}
</script>

<style scoped>
.content {
  margin: 0;
  padding: 0;
  position: absolute;

  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);

  width: 20%;
  height: 50%;

  background-color: yellow;
  border: 1px solid black;
}

.login {
    margin: 40px 5px 25px 40px;
}

.password {
  margin: 40px 5px 25px 40px;
}

#logginButton {
  margin: 0;
  padding: 0;
  position: absolute;
  bottom: 50%;
  left: 50%;
  transform: translate(-50%, 0);
  width: 70%;
  height: 50px;
}

#registerButton {
  margin: 0;
  padding: 0;
  position: absolute;
  bottom: 25%;
  left: 50%;
  transform: translate(-50%, 0);
  width: 70%;
  height: 50px;
}

@media only screen and (max-width: 777px) {
  .content {
    width: 90%;
    height: 800px;
  }

  #loginTextinput {
    width: 80%;
    margin: 0;
    padding: 0;
    height: 100px;
    font-size: xx-large;
  }

  #passwordTextinput {
    width: 80%;
    margin: 0;
    padding: 0;
    height: 100px;
    font-size: xx-large;
  }
}

@media only screen and (min-width: 778px) and (max-width: 1244px) {
  .content {
    width: 700px;
    height: 800px;
  }

  #loginTextinput {
    width: 80%;
    margin: 0;
    padding: 0;
    height: 100px;
    font-size: xx-large;
  }

  #passwordTextinput {
    width: 80%;
    margin: 0;
    padding: 0;
    height: 100px;
    font-size: xx-large;
  }
}

</style>