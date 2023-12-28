<template>
  <div class="wrapper">
    <input type="checkbox" id="btn" hidden />
    <label for="btn" class="menu-btn">
      <i class="fa-solid fa-bars"></i>
    </label>
    <nav id="sidebar">
      <div class="title">Menu</div>

      <ul class="list-items">
        <router-link to=""></router-link>
        <li><router-link to="/Home">Inbox</router-link></li>
        <li><router-link to="/Contacts">Contacts</router-link></li>
        <li><router-link to="/Folder">Folders</router-link></li>
        <li><router-link to="/Draft">Draft</router-link></li>
        <li><router-link to="/Sent">Sent</router-link></li>
        <li><router-link to="/Trash">Trash</router-link></li>
        <div class="icons" style="diplay: flex; flex-direction: column">
          <h4 style="color: aliceblue">{{ user_name }}</h4>
          <h6 style="color: aliceblue">{{ email }}</h6>
        </div>
      </ul>
    </nav>
  </div>
  <div class="content">
    <div class="header">

      <h1 style="color: aliceblue; padding-top: 10px">O3M-Mail</h1>
      <button style="width:auto; font-size:large;  background-color:darkgrey;  height:40px;margin:auto; margin-left:2% ;" @click="reload()">reload</button>
      <h1 class="box-title">INBOX</h1>
      <button style="width:auto; background-color:darkgrey; border-radius: 10px; font-size:large; height:50px;margin:auto;margin-left:30% ;" @click="setting()">Setting <i  style="font-size:40px" class="fa-solid fa-gear"></i></button>
      
    </div>
    <div class="b2">
      <div class="container">
        <div class="operations">
          <div class="filter">
            <input v-model="search" style="margin-left:5px;" placeholder=" search...." for="searchtypeid">
            <div class="search">
              <select v-model="searchby" id="searchtypeid" name="searchtype">
                <option>Search by</option>
                <option>Date</option>
                <option>Sender</option>
                <option>Recevier</option>
                <option>Priority</option>
                <option>Subject</option>
                <option>Content</option>
                <option>Attachments</option>
              </select>
              <button @click="srch()" >Search</button>
            </div>
          </div>
          <div class="filter">
            <input v-model="filterid" style="margin-left:5px;" placeholder=" filterby...." for="searchtypeid">

            <div class="select">
              <select v-model="filterby" id="filterid" name="filter">
                <option>Filter by</option>
                <option>Sender-Subject</option>
                <option>Subject-Priority</option>
                <option>Sender-Priority</option>
              </select>
              <button @click="filter()">filter</button>
            </div>
          </div>
          <div class="filter">
            <div class="sort">
              <select v-model="sortby" id="sortid" name="sort">
                <option>Sort by</option>
                <option>Date</option>
                <option>Sender</option>
                <option>Recevier</option>
                <option>Priority</option>
                <option>Subject</option>
                <option>Content</option>
                <option>Attachment</option>
              </select>
              <button style="margin-left:15px" @click="sort()">sort</button>
            </div>
          </div>

          
          <button class="new" @click="delet()">Delete the selected <i class="fa-solid fa-trash" style="font-size:25px; color:red"></i></button>
          <button class="new" @click="folder_selected()">Folder selected <i class="fa-solid fa-up-down-left-right"></i></button>
          <button class="new" @click="dia()">new massage</button>
        </div>
        <v-dialog v-model="dialog" width="800" heigth="850"  dark hide-overlay persistent>
          <v-card>
            <v-card-title style="color:white; background-color:#3498db; padding:auto; text-align:center; font-size:35px">new massage</v-card-title>
            <v-card-text style="background-color:black">
              <v-form>
                <label style="font-size:20px; font:bold; color:#3498db;background-color:black">To:</label>
                <v-btn @click="multyres()" style="width:auto;rigth:0;margin-left:200px;margin-bottom:10px;background-color:darkgoldenrod;color:black">clock to multy recievers</v-btn>
                <br>
                <input v-if="!multi" v-model="massage.receiver" @input="checkEmailValidity" style="width: 600px" type="email" placeholder="user@CSED.com" id="toid">
                <input v-if="multi" v-model="massage.receiver" @input="checkEmailValidity" style="width: 600px" type="email" placeholder="user1@CSED.com-user2@CSED.com-user3@CSED.com" id="toid">
                <span v-if="isToInvalid" style="color: red;">Invalid email format</span>
                <br>
                <label style="font-size:20px; font:bold; color:#3498db;background-color:black">From:</label>
                <br>
                <input style="width:600px" type="text" placeholder=" useremail" :value="this.email"  disabled>
                <br>
                <label style="font-size:20px; font:bold; color:#3498db;background-color:black">subject:</label>
                <br>
                <input v-model="massage.subject" style="width:600px" type="text" placeholder=" (0-30)characters">
                <br>
                <label style="font-size:20px; font:bold; color:#3498db;background-color:black ">content</label>
                <br>
                <textarea v-model="massage.content" id="userInput" name="userInput" class="left-up-align" rows="2" style="width:600px;border-raduis:10px;"></textarea>
              </v-form>
              <label style="font-size:20px; font:bold; color:#3498db;background-color:black" for="#" >attachments:</label>
            </v-card-text>
            <input id="fileupload" type="file" name="fileupload" multiple />
            <v-card-actions style="background-color:black">
              
              <label style="font-size:20px; font:bold; color:#3498db;background-color:black" for="menu">priority:</label>
              <select id="menu" name="menu" v-model="massage.priority">
                <option value="1">1 (High)</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5 (Low)</option>
              </select>
              <v-btn style="width:auto; margin-left:20px" @click="draft()">Save as a draft</v-btn>
              <v-btn style="width:auto; margin-left:200px" @click="send()">Send</v-btn>
              <v-btn style="width:auto; margin-left:60px" @click="dia()">close</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
         <!-- // ///////////////////////////////////////////////////////-->
        <v-dialog v-model="folder" width="600" heigth="600"  dark hide-overlay persistent>
          <v-card>
            <v-card-title style="color:white; background-color:#3498db; padding:auto; text-align:center; font-size:35px">Folders<i style="font-size:50px; margin-left:5px;" class="fa-solid fa-folder-plus"> </i></v-card-title>
            <v-card-text style="background-color:black">
              <v-form>
                <label v-if="!createNewVisible" style="font-size:20px; font:bold; color:#3498db;background-color:black" for="menu">select sender folders:</label>
                <select v-if="!createNewVisible" v-model="foldername"  id="searchtypeid" name="searchtype">
                  <option v-for="folder in folders_name" :key="folder" :option=folder_name >{{ folder }}</option>
                </select>
                <v-btn v-if="!createNewVisible" style="width:auto; margin-left:20px" @click="addtofolder()">Add To Folder</v-btn>

                <label v-if="createNewVisible" style="font-size:20px; font:bold; color:#3498db;background-color:black">creat new:</label>
                <br v-if="createNewVisible">
                <input v-if="createNewVisible" v-model="newfoldername" style="width:400px" type="text"  placeholder="new folder name">
                <v-btn v-if="createNewVisible" style="width:auto;margin-top:15px; margin-left:10px" @click="createnew()">create and add to folder</v-btn>
                </v-form>
            </v-card-text>
            
            <v-card-actions style="background-color:black">
              
              <v-btn style="width:auto; margin-left:500px" @click="closefolder()">close</v-btn>
            </v-card-actions>
            <v-card-actions style="background-color:black">
              
              <v-btn style="width:90%; margin:auto; " @click="showCreateNew()">Create other folder</v-btn></v-card-actions>
          </v-card>
        </v-dialog>
        <!-- // ///////////////////////////////////////////////////////-->
        <v-dialog v-model="multifolder" width="600" heigth="600"  dark hide-overlay persistent>
          <v-card>
            <v-card-title style="color:white; background-color:#3498db; padding:auto; text-align:center; font-size:35px">Folders<i style="font-size:50px; margin-left:5px;" class="fa-solid fa-folder-plus"> </i></v-card-title>
            <v-card-text style="background-color:black">
              <v-form>
                <label v-if="!createNewVisible" style="font-size:20px; font:bold; color:#3498db;background-color:black" for="menu">select sender folders:</label>
                <select v-if="!createNewVisible" v-model="foldername"  id="searchtypeid" name="searchtype">
                  <option v-for="folder in folders_name" :key="folder" :option=folder_name >{{ folder }}</option>
                </select>
                <v-btn v-if="!createNewVisible" style="width:auto; margin-left:20px" @click="folderselected()">Add To Folder</v-btn>

                <label v-if="createNewVisible" style="font-size:20px; font:bold; color:#3498db;background-color:black">creat new:</label>
                <br v-if="createNewVisible">
                <input v-if="createNewVisible" v-model="newfoldername" style="width:400px" type="text"  placeholder="new folder name">
                <v-btn v-if="createNewVisible" style="width:auto;margin-top:15px; margin-left:10px" @click="folderselected()">create and add to folder</v-btn>
                </v-form>
            </v-card-text>
            
            <v-card-actions style="background-color:black">
              
              <v-btn style="width:auto; margin-left:500px" @click="closemultifolder()">close</v-btn>
            </v-card-actions>
            <v-card-actions style="background-color:black">
              
              <v-btn style="width:90%; margin:auto; " @click="showCreateNew()">Create other folder</v-btn></v-card-actions>
          </v-card>
        </v-dialog>
        <!-- // ///////////////////////////////////////////////////////-->
        <v-dialog v-model="contacts" width="600" heigth="600"  dark hide-overlay persistent>
          <v-card>
            <v-card-title style="color:white; background-color:#3498db; padding:auto; text-align:center; font-size:35px">Contacts<i style="font-size:50px; margin-left:5px;" class="fa-solid fa-user-plus"></i>  </v-card-title>
            <v-card-text style="background-color:black">
              <v-form>
                <label style="font-size:20px; font:bold; color:#3498db;background-color:black">name:</label>
                <input v-model="newname" type="text">
                <br>
                <label style="font-size:20px; font:bold; color:#3498db;background-color:black">email:</label>
                <input :value="this.itemsender" style="width:400px" type="text"   disabled>
                <v-btn style="width:auto;margin-top:15px; margin-left:10px" @click="addtocontacte()">add to contacts</v-btn>
                </v-form>
            </v-card-text>
            <v-card-actions style="background-color:black">
              <v-btn style="width:auto; margin-left:500px" @click="closecontact()">close</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <!-- ////////////////////////////////////////////////////////////////////////////// -->
        <v-dialog v-model="sittingdialog" width="600" heigth="600"  dark hide-overlay persistent>
          <v-card>
            <v-card-title style="color:white; background-color:#3498db; padding:auto; text-align:center; font-size:35px">Setting  </v-card-title>
            <v-card-text style="background-color:black">
              <v-form>
                <label style="font-size:20px; font:bold; color:#3498db;background-color:black">status</label>
                <select id="menu" name="menu" v-model="status">
                  <option value="online">online</option>
                  <option value="in calling">in calling</option>
                  <option value="busy">busy</option>
                </select>
                <br>
                <v-btn style="width:auto;margin-top:15px; margin-left:10px" @click="logout()">LOgout<i style="font-size:25px"  class="fa-solid fa-right-sender-bracket"></i></v-btn>
                </v-form>
            </v-card-text>
            <v-card-actions style="background-color: black; display: flex; justify-content:right;">
              <v-btn style="width: auto;" @click="applay()">Apply</v-btn>
              <v-btn style="width: auto;" @click="closesitting()">Close</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>

        <div class="info">
          <label for="pageSlider" class="slider-label">Page:</label>
          <input
            id="pageSlider"
            type="range"
            :min="1"
            :max="totalPages"
            v-model="currentPage"
            @input="handlePageChange"
            class="page-slider"
          />
          <span class="current-page">{{ currentPage }}</span>
         <table border="2px">
          <tr>
            <td>       </td>
            <td>priority</td>
            <td>Sender</td>
            <td>Date</td>
            <td> subject</td>
            <td>Content</td>
            <td>Attachments</td>
            <td>Move to trash <i class="fa-solid fa-trash" style="font-size:15px; color:red"></i></td>
           
          </tr>
          <tr  v-for="index in getRangeStartToEnd(start, end)" :key="index">
            <template v-if="emails[index]">
            <td>  <input  @click="print(emails[index].id)" :value="emails[index].id" type="checkbox">   <i @click="folddialog(emails[index].id)" class="fa-solid fa-folder-plus"> </i> <i @click="contactdialog(emails[index].id,emails[index].sender)" class="fa-solid fa-user-plus"></i> </td>
            <td>{{ emails[index].priority }}</td>
            <td>{{ emails[index].sender }}</td>
            <td>{{ emails[index].date }}</td>
            <td>{{ emails[index].subject }}</td>
            <td>{{ emails[index].content }}</td>
            <td>
              <a v-for="attach in item.attachments" :key="attach.id"  @click="downloadAttachment(attach.id, attach.attachmentName, item.id)">{{ attach.attachmentName }}</a>  </td>
            <td><i @click="trash(emails[index].id)" class="fa-solid fa-trash" style="font-size:25px; color:red;"></i></td>
          </template>
          </tr>
         </table>
        </div>
        <div class="usersinformation">
          <div class="box" v-for="user in users" :key="user.id">
            <h1>{{ user }}</h1>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script >

export default {
  name: "HoMe",
  
  data() {
    return {
      user_name:'' ,
      email: '',
      dialog:false,
      search: '',
      searchby:'',
      sortby:'',
      filterby:'',
      filterid:'',
      choosen:[],
      folder:false,
      foldername:'',
      itemid:'',
      newfoldername:'',
      contacts:false,
      newname:'',
      createNewVisible: false,
      sittingdialog:false,
      itemsender:'',
      nameuse:'',
      massage:{
        sender: '',
        receiver:'',
        subject:'',
        content:'',
        priority:'',
        attachments:[],
      },
      folders_name:[],
      emails: [],
      ids: [],
      multifolder: false,
      currentPage: 1,
      itemsPerPage: 3,
      choos:'',
      multi:false
    };
  },
  computed: {
    start() {
      return (this.currentPage - 1) * this.itemsPerPage;
    },
    end() {
      return this.start + this.itemsPerPage - 1;
    },
    totalPages() {
      return Math.ceil(this.emails.length / this.itemsPerPage);
    },
  },
  async mounted() {
    this.user_name = JSON.parse(localStorage.getItem("person-inf")).userName
    this.email = JSON.parse(localStorage.getItem("person-inf")).email;
    console.warn(this.email)
    this.massage.sender = this.email
    await fetch(`http://localhost:8080/mail/inbox?id=${this.email}`, {
      method: "GET",
    }).then(res => res.json())
    .then(data =>this.emails=data);
    this.totalPages = Math.ceil(this.emails.length / 5)
  },
  methods: {
    print(ind){
      console.log(ind)
      this.choosen.push(ind)
      console.log(this.choosen)
    },
    getRangeStartToEnd(start, end) {
      return Array.from({ length: end - start + 1 }, (_, index) => start + index);
    },
    handlePageChange() {
      this.start=this.currentPage
      console.log('Page changed to:', this.currentPage);
    },
    dia(){
      this.dialog=!this.dialog
    },
     async folddialog(ind){
      this.folder=true
      this.itemid=ind
      this.user_name = JSON.parse(localStorage.getItem("person-inf")).userName
    this.email = JSON.parse(localStorage.getItem("person-inf")).email;
    console.warn(this.email)
    this.massage.sender = this.email
      await fetch(`http://localhost:8080/folders?id=${this.email}`,{
          method:"GET"
      }).then(res=>res.json())
      .then(data=>this.folders_name=data);
      console.warn(this.folders_name)
    },
    async folder_selected(){
        this.multifolder = true
        await fetch(`http://localhost:8080/folders?id=${this.email}`,{
          method:"GET"
      }).then(res=>res.json())
      .then(data=>this.folders_name=data);
      console.warn(this.folders_name)
    },
    closefolder(){
      this.folder=false
      this.createNewVisible=false
    },
    closemultifolder(){
      this.multifolder=false
      this.createNewVisible=false
    },
    contactdialog(ind,its){
      this.contacts=true
      this.itemid=ind
      this.itemsender=its
    },
    closecontact(){
      this.contacts=false
    },
    setting(){
      this.sittingdialog=true
    },
    showCreateNew(){
      this. createNewVisible=!this.createNewVisible
    },
    multyres(){
      this.multi=true;
    },
    clear(){
      this.massage.receiver=''
      this.massage.sender=''
      this.massage.attachments=[]
      this.massage.subject=''
      this.massage.content=''
      this.massage.priority=''

    },
    closesitting(){
      this.sittingdialog=false
    },
    applay(){
      JSON.stringify(localStorage.getItem("user-info")).status=this.status 
      this.sittingdialog=false     
    },
    logout(){
      localStorage.clear();
      this.$router.push('/')
    },
    reload(){
      location.reload();
    }
    ,
    checkEmailValidity() {
      const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      this.isToInvalid = !this.massage.receiver.match(emailPattern);
    },
   async draft(){
        this.dialog=false
        await fetch("http://localhost:8080/mail/draft/create",{
          method:"POST",
          headers: {
          Accept : "application/json",
          "content-type" : "application/json"
        },
          body:JSON.stringify(this.massage)
        }).catch((error)=>{
          console.log("Error",error)
        });
    },
   async send(){
    await this.uploadFile();
    await fetch(`http://localhost:8080/mail/sent/create`,{
        method:"POST",
        headers: {
          Accept : "application/json",
          "content-type" : "application/json"
        },
        body:JSON.stringify(this.massage)
      })
      localStorage.setItem("massage",JSON.stringify(this.massage))
      this.clear()
      this.dialog=false
      location.reload()
    },
   async srch(){
    console.log(this.searchby,this.search)
   
     await fetch(`http://localhost:8080/mails/inbox/search?id=${this.email}&type=${this.searchby}&keyWord=${this.search}`,{
        method:"GET"
   }).then(res => res.json())
    .then(data =>this.emails=data);
    this.searchby=''
    this.search=''
    console.warn(this.emails)
    },
    async filter(){
    console.log(this.filterby,this.filterid)
    await fetch(`http://localhost:8080/inbox/filter?id=${this.email}&criteria=${this.filterby}&keyWord=${this.filterid}`,{
        method:"GET"
   }).then(res => res.json())
    .then(data =>this.emails=data);
    this.filterid=''
    this.filterby=''
    },
    async sort(){
      console.log(this.sortby)
     await fetch(`http://localhost:8080/mails/inbox/sort?id=${this.email}&type=${this.sortby}`,{
        method:"GET"
     }).then(res => res.json())
    .then(data =>this.emails=data);
    this.sortby='' 
    },
   async trash(ind){
    this.ids[0] = ind
      console.log("size "+this.choosen.length)
      console.log(this.choosen[2])
      console.warn(JSON.stringify(this.emails[ind]));
      await fetch(`http://localhost:8080/mail/inbox/trash?id=${this.email}&ids=${this.ids}`,{
        method:"DELETE"
     })
     location.reload();
    },
    async delet() {
      if(this.choosen.length===0)
      {
        alert("please select email ")
      }
      else{
      await fetch(`http://localhost:8080/mail/inbox/trash?id=${this.email}&ids=${this.choosen}`,{
        method:"DELETE",
     })
      location.reload();
    }
    },
    async addtofolder(){
      console.warn(this.itemid,this.foldername)
      this.ids[0] = this.itemid
      await fetch(`http://localhost:8080/folders/add?id=${this.email}&folderName=${this.foldername}&ids=${this.ids}`,
      {
        method:"POST",
      })
      this.folder=false
      this.multifolder = false
      this.foldername=''
    },
    async folderselected(){
      console.warn(this.itemid,this.newfoldername)
      await fetch(`http://localhost:8080/folders/add?id=${this.email}&folderName=${this.newfoldername}&ids=${this.choosen}`,
      {
        method:"POST",
      })
      this.folder=false
      this.multifolder = false
      this.newfoldername=''
    },
   async createnew(){
    console.warn(this.itemid,this.newfoldername)
    this.ids[0] = this.itemid
      await fetch(`http://localhost:8080/folders/add?id=${this.email}&folderName=${this.newfoldername}&ids=${this.ids}`,
      {
        method:"POST",
      })
      this.folder=false
      this.newfoldername=''
    },
   async addtocontacte(){
   
    console.warn(this.newname,this.itemsender)
      await fetch(`http://localhost:8080/contact/create?id=${this.email}&name=${this.newname}&email=${this.itemsender}`,
      {
        method:"POST",
      })
      this.contacts=false
      this.newname=''
    },
    async uploadFile() {
        let formData = new FormData();
        let files = document.getElementById('fileupload').files;
        for (let i = 0; i < files.length; i++) {
            formData.append("data", files[i]);
        }
        await fetch('http://localhost:8080/mail/sent/attachments', {
            method: "POST",
            body: formData
        }).then(res => res.json())
        .then(data => this.massage.attachments = data);
    },
    async downloadAttachment(attachmentId, attachmentName,mailId){
      let res = await fetch(`http://localhost:8080/mail/attachments/sent/download?id=${this.email}&mailId=${mailId}&attachmentId=${attachmentId}`,{
        method : "GET"
      });
      if(res.ok){
        console.alert(`${attachmentName} dowanloaded`)
      }
    },
    getEmailId(item) {
      return item ? item.id || '' : '';
    },
   }
};
</script>

<style scoped>
.slider-label {
  margin-right: 10px;
  color: rgb(9, 2, 2);
  font-weight: bold; 
}

.page-slider {
  background: white; 
}


.current-page {
  margin-left: 5px;
  color: white;
  font-weight: bold; 
}

.page-slider::-webkit-slider-thumb {
  transition: transform 0.3s ease-in-out;
}

.page-slider:hover::-webkit-slider-thumb {
  transform: scale(1.2); 
}
@import url("https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap");
input[type="file"] {
  color: #3498db; 
  background-color: black;
  padding: 10px; 
  
  
}
.box-title {
  color: aliceblue;
  margin-left:8%;
  text-align: center;
  padding-top: 10px;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
  font-size: 36px; 
  transition: color 0.3s ease; 
}
.box-title:hover {
  color:  #3498db; 
}
.new {
  width:auto;
  height: 60px;
  margin-top: 15px;
  margin-left: 2%;
  background-color:#3498db; 
  color: white; 
  text-align: center; 
  font-size: 16px;
  cursor: pointer; 
  transition: transform 0.3s ease-in-out; 
}


.new:hover {
  transform: scale(1.1); 
}
table {
  border-collapse: collapse;
  width: 100%;
  box-shadow: 0 0 10px #3498db;
  
}

table, th, td {
  border: 2px solid rgb(19, 231, 223);
  
}

th, td {
  padding: 10px;
  text-align: left;
}

tr:hover {
  background-color: #f5f5f5;
}

td {
  transition: background-color 0.3s ease;
  background-color: #201515;
  color: #fff;
}

td:hover {
  box-shadow: 0 0 15px #08daff;
}
.info {
  padding-top: 5px;
  

}
.filter {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.content {
  z-index: -1;
}
select {
  width: 110px;
  border: 2px solid transparent;
  border-radius: 5px;
}
select:hover {
  border-color: #3498db;
}
button {
  width: 80px;
  border-radius: 5px;
  border: 2px solid transparent;
  transition: border-color 0.3s ease; /* Add transition for smooth animation */
  cursor: pointer;
  background-color: #3498db;
  box-shadow: 0 0 5px #3498db;
  color: white;
}

button:hover {
  border-color: #3498db;
  transform: scale(1.1);
  
}

@keyframes bounce {
  0%,
  20%,
  50%,
  80%,
  100% {
    transform: translateY(0); 
  }
  40% {
    transform: translateY(-10px); 
  }
  60% {
    transform: translateY(-5px); 
  }
}
label {
  margin: 5px;
  background-color: #fff;
  border-radius: 5px;
}
select {
  margin: 5px;
}
.operations {
  padding-top: 5px;
  border: #333 solid 2px;
  height: 90px;
  border-radius: 5px;
  display: flex;
  flex-direction: row;

  gap: 40px;
  z-index: -1;
  box-shadow: 0 0 15px #3498db;
}
.b2 {
  background: url("../assets/stars.jpg");
}
.container {
  margin-top: 3px;
  width: 3000px;
  border: rgb(255, 255, 255) solid 3px;
  height: 100vh;
  z-index: -1;
  border-radius: 25px;
  background: url("../assets/back.jpg");
  padding: 20px;
}
.header {
  background-color: #4a4a4a;
  margin-top: 0;
  top: 0;
  height: 60px;
  display: flex;
  flex-direction: row;
  padding-left: 100px;
}
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Poppins", sans-serif;
}
.wrapper {
  height: 100%;
  width: 300px;
  position: relative;
}
.wrapper .menu-btn {
  position: absolute;
  left: 20px;
  top: 10px;
  background: #4a4a4a;
  color: #fff;
  height: 45px;
  width: 45px;
  z-index: 9999;
  border: 1px solid #333;
  border-radius: 5px;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 0.3s ease;
}
#btn:checked ~ .menu-btn {
  left: 247px;
}
.wrapper .menu-btn i {
  position: absolute;
  transform: "";
  font-size: 23px;
  transition: all 0.3s ease;
}
.wrapper .menu-btn i.fa-times {
  opacity: 0;
}
#btn:checked ~ .menu-btn i.fa-times {
  opacity: 1;
  transform: rotate(-180deg);
}
#btn:checked ~ .menu-btn i.fa-bars {
  opacity: 0;
  transform: rotate(180deg);
}
#sidebar {
  position: fixed;
  background: #404040;
  height: 100%;
  width: 270px;
  overflow: hidden;
  left: -270px;
  transition: all 0.3s ease;
}
#btn:checked ~ #sidebar {
  left: 0;
}
#sidebar .title {
  line-height: 65px;
  text-align: center;
  background: #333;
  font-size: 25px;
  font-weight: 600;
  color: #f2f2f2;
  border-bottom: 1px solid #222;
}
#sidebar .list-items {
  position: relative;
  background: #404040;
  width: 100%;
  height: 100%;
  list-style: none;
}
#sidebar .list-items li {
  padding-left: 40px;
  line-height: 50px;
  border-top: 1px solid rgba(255, 255, 255, 0.1);
  border-bottom: 1px solid #333;
  transition: all 0.3s ease;
}
#sidebar .list-items li:hover {
  border-top: 1px solid transparent;
  border-bottom: 1px solid transparent;
  box-shadow: 0 0px 10px 3px #222;
}
#sidebar .list-items li:first-child {
  border-top: none;
}
#sidebar .list-items li a {
  color: #f2f2f2;
  text-decoration: none;
  font-size: 18px;
  font-weight: 500;
  height: 100%;
  width: 100%;
  display: block;
}
#sidebar .list-items li a i {
  margin-right: 20px;
}
#sidebar .list-items .icons {
  width: 100%;
  height: 40px;
  text-align: center;
  position: absolute;
  bottom: 100px;
  line-height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
}
#sidebar .list-items .icons a {
  height: 100%;
  width: 40px;
  display: block;
  margin: 0 5px;
  font-size: 18px;
  color: #f2f2f2;
  background: #4a4a4a;
  border-radius: 5px;
  border: 1px solid #383838;
  transition: all 0.3s ease;
}
#sidebar .list-items .icons a:hover {
  background: #404040;
}
.list-items .icons a:first-child {
  margin-left: 0px;
}
.content .header {
  font-size: 45px;
  font-weight: 700;
}
.content p {
  font-size: 40px;
  font-weight: 700;
}
</style>
