<template>
  <section id="form" class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-12 col-md-8 col-lg-6 col-xl-5">
          <div class="card bg-dark text-white" style="border-radius: 1rem">
            <div class="card-body p-5 text-center">
              <div class="mb-md-5 mt-md-4 pb-5">
                <h2 class="fw-bold mb-2 text-uppercase">Login</h2>
                <p class="text-white-50 mb-5">
                  Please enter your email and password!
                </p>

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
                  Login
                </button>
              </div>
              <div>
                <p class="mb-0">
                  Don't have an account?
                  <router-link to="/Sign-UP">Sign Up</router-link>
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
  name: "LogIn",
  data() {
    return {
      email1: "",
      password1: "",
      isEmailInvalid: false,
      person: {
        email: "",
        password: "",
        status:'online'  //by default
      },
    };
  },
  methods: {
    validate() {
      var mail = document.getElementById("typeEmailX").value;
      console.log(mail);
      var patern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if (mail.match(patern) && this.password1!=='') {
        this.login();
        console.log("ksdml")
        this.isEmailInvalid = false;
      } else {
        alert("enter valid email and sure there is no feld empty!!");
        console.log("invalid");
        this.isEmailInvalid = true;
      }
    },
    async login() {
      this.person.email = this.email1;
      this.person.password = this.password1;
      localStorage.setItem("person-inf", JSON.stringify(this.person));
      localStorage.setItem( JSON.stringify(this.person));
      let res = await fetch(`http://localhost8080?$email=${this.person.email},password=${this.person.password}`, {
        method: "GET",
      }).catch((error) => {
        console.error("Fetch error:", error);
      });
      if (res.ok) {
        this.$router.push("/Home");
      } else {
        alert("please check email and password again");
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
