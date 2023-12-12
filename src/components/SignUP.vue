<template>
  <section id="form" class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-12 col-md-8 col-lg-6 col-xl-5">
          <div class="card bg-dark text-white" style="border-radius: 1rem">
            <div class="card-body p-5 text-center">
              <div class="mb-md-5 mt-md-4 pb-5">
                <h2 class="fw-bold mb-2 text-uppercase">Sign Up</h2>
                <p class="text-white-50 mb-5">
                  Welcome, Please enter your data
                </p>
                <div
                  class="form-outline form-white mb-4"
                  :class="{ 'has-error': isuserInvalid }"
                >
                  <input
                    type="text"
                    id="typeUsernameX"
                    class="form-control form-control-lg"
                    v-model="this.username1"
                    required
                  />
                  <label class="form-label" for="typeUsernameX"
                    >User name</label
                  >
                </div>
                <div
                  class="form-outline form-white mb-4"
                  :class="{ 'has-error': isEmailInvalid }"
                >
                  <input
                    type="email"
                    id="typeEmailX"
                    class="form-control form-control-lg"
                    v-model="this.email1"
                    required
                    @blur="validate"
                  />
                  <label class="form-label" for="typeEmailX" id="email"
                    >Email</label
                  >
                </div>

                <div class="form-outline form-white mb-4">
                  <input
                    type="password"
                    id="typePasswordX"
                    class="form-control form-control-lg"
                    v-model="this.password1"
                    required
                  />
                  <label class="form-label" for="typePasswordX">Password</label>
                </div>
                <button
                  class="btn btn-outline-light btn-lg px-5"
                  @click="validate()"
                  type="submit"
                >
                  SIGN UP
                </button>
              </div>
              <div>
                <p class="mb-0">
                  I have an account
                  <router-link to="/">Login</router-link>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "SignU",
  data() {
    return {
      username1: "",
      email1: "",
      password1: "",
      isEmailInvalid: false,
      isuserInvalid: false,
      person: {
        username: "",
        email: "",
        password: "",
      },
    };
  },
  methods: {
    validate() {
      var mail = document.getElementById("typeEmailX").value;
      console.log(mail);
      var patern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      console.log(this.username1);
      if (mail.match(patern) && this.username1.trim() !== "") {
        this.login();
        this.isEmailInvalid = false;
        this.isuserInvalid = false;
      } else if (this.username1.trim() === "") {
        alert("Username cannot be empty");
        this.isUserInvalid = true;
      } else {
        alert("Enter a valid email");
        console.log("Invalid email");
        this.isEmailInvalid = true;
      }
    },
    async login() {
      this.person.username = this.username1;
      this.person.email = this.email1;
      this.person.password = this.password1;
      localStorage.setItem("person-inf", JSON.stringify(this.person));
      let res = await fetch("http://localhist8080/", {
        method: "POST",
        body: JSON.stringify(this.person),
      }).catch((error) => {
        console.error("Fetch error:", error);
      });
      if (res.ok) {
        this.$router.push("/Home");
      } 
    },
  },
};
</script>

<style scoped>
.has-error input {
  border: 2px solid red;
}
</style>
