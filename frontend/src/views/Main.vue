<template>
  <button id="logoutButton" @click="logout">Logout</button>

  <div id="inputDiv">
    Choose x:
    <input id="xTextinput" type="text" name="xval" maxlength="9" autocomplete="off" placeholder="Number from -3 to 5" v-model="x">
    <br/><br/>
<!--    <select id="xSelect" v-model="x">-->
<!--      <option value="-5">-5</option>-->
<!--      <option value="-4">-4</option>-->
<!--      <option value="-3">-3</option>-->
<!--      <option value="-2">-2</option>-->
<!--      <option value="-1">-1</option>-->
<!--      <option value="0">0</option>-->
<!--      <option value="1">1</option>-->
<!--      <option value="2">2</option>-->
<!--      <option value="3">3</option>-->
<!--    </select>-->
<!--    <br/><br/>-->


    Choose y:
    <input id="yTextinput" type="text" name="yval" maxlength="9" autocomplete="off" placeholder="Number from -3 to 3" v-model="y">
    <br/><br/>

    Choose r:
    <input id="rTextinput" type="text" name="rval" maxlength="9" autocomplete="off" placeholder="Number from -3 to 5" v-model="r" @input="drawPoints">
    <br/><br/>
<!--    <select id="rSelect" v-model="r" @change="drawPoints">-->
<!--      <option value="-5">-5</option>-->
<!--      <option value="-4">-4</option>-->
<!--      <option value="-3">-3</option>-->
<!--      <option value="-2">-2</option>-->
<!--      <option value="-1">-1</option>-->
<!--      <option value="0">0</option>-->
<!--      <option value="1">1</option>-->
<!--      <option value="2">2</option>-->
<!--      <option value="3">3</option>-->
<!--    </select>-->

    <button class="inputButton" type="reset" id="resetButton" @click="resetPoints">Reset</button>
    <button class="inputButton" id="submitButton" @click="submitForm">Submit</button>
  </div>

  <div id="coordsDiv" @click="sendPointFromCoords" style="cursor: pointer;">
    <img id="coordsImg" src="../assets/Lab4Coordinates.png" style="position: absolute; object-fit: fill; width: 500px; height: 500px;">
    
    <div class="rOnCoords" id="top2R">{{r}}</div>
    <div class="rOnCoords" id="topR">{{r/2}}</div>
    <div class="rOnCoords" id="right2R">{{r}}</div>
    <div class="rOnCoords" id="rightR">{{r/2}}</div>
    <div class="rOnCoords" id="bottom2R">{{-r}}</div>
    <div class="rOnCoords" id="bottomR">{{-r/2}}</div>
    <div class="rOnCoords" id="left2R">{{-r}}</div>
    <div class="rOnCoords" id="leftR">{{-r/2}}</div>
  </div>

  <div id="tableDiv" style="overflow: scroll;">
    <div id="maintable">
      <div class="table">
        <table style="width: 100%">

          <table id="result-table">
            <tr class="table-header">
              <th class="coords-col"> X </th>
              <th class="coords-col">Y</th>
              <th class="coords-col">R</th>
              <th class="hitres-col"> Hit result</th>
              <th class="time-col"> Excution time</th>
            </tr>
            <tr v-for="point in points" :key="point">
              <td class="coords-col">{{point.x.toFixed(3)}}</td>
              <td class="coords-col">{{point.y.toFixed(3)}}</td>
              <td class="coords-col">{{point.r}}</td>
              <td class="hitres-col">{{point.hitResult}}</td>
              <td class="time-col">{{point.time}}</td>
            </tr>
          </table>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    name: "Main",
    data(){
        return {
            x: "0",
            y: "0",
            r: "3",
            points: new Array()
        }
    },
  created() {
      this.getPoints();
      this.drawPoints();
  },
  watch: {
      x(value) {
        const buttonSubmit = document.getElementById("submitButton");
        const yTextInput = document.getElementById("xTextinput");
        if (!isNaN(parseFloat(value)) && isFinite(value) && -3 <= value && value <= 5) {
          //good
          buttonSubmit.disabled = false;
          buttonSubmit.style.border = "2px solid black";
          yTextInput.style.backgroundColor = "white";
        } else {
          //bad
          buttonSubmit.disabled = true;
          buttonSubmit.style.border = "2px solid red";
          yTextInput.style.backgroundColor = "red";
        }
      },
      y(value) {
        const buttonSubmit = document.getElementById("submitButton");
        const yTextInput = document.getElementById("yTextinput");
        if (!isNaN(parseFloat(value)) && isFinite(value) && -3 <= value && value <= 3) {
          //good
          buttonSubmit.disabled = false;
          buttonSubmit.style.border = "2px solid black";
          yTextInput.style.backgroundColor = "white";
        } else {
          //bad
          buttonSubmit.disabled = true;
          buttonSubmit.style.border = "2px solid red";
          yTextInput.style.backgroundColor = "red";
        }
      },
      r(value) {
        const buttonSubmit = document.getElementById("submitButton");
        const rSelect = document.getElementById("rTextinput");
        if (!isNaN(parseFloat(value)) && isFinite(value) && -3 <= value && value <= 5) {
          //good
          buttonSubmit.disabled = false;
          buttonSubmit.style.border = "2px solid black";
          rSelect.style.backgroundColor = "white";

          if(value < 0) {
            let coordImage = document.getElementById("coordsImg");
            coordImage.style.transform = "rotate(180deg)";
          } else {
            let coordImage = document.getElementById("coordsImg");
            coordImage.style.transform = "rotate(0)";
          }
        } else {
          //bad
          buttonSubmit.disabled = true;
          buttonSubmit.style.border = "2px solid red";
          rSelect.style.backgroundColor = "red";
        }
      }
    },

    methods: {
        submitForm(event) {
            event.preventDefault();
            const userDetails = JSON.parse(localStorage.getItem("userDetails"));
            if (userDetails) {
                this.axios.post("http://localhost:8081/points", {
                    x: this.x,
                    y: this.y,
                    r: this.r
                },
                {
                    headers: {"Authorization": "Bearer " + userDetails.accessToken}
                }).then(() => {
                    this.getPoints();
                }).catch(error => {
                    if (error.response.status == 401 || error.response.status == 403) {
                        this.invalidateTokenAndGoToAuthPage();
                    }
                })
            } else this.invalidateTokenAndGoToAuthPage();
        },
        resetPoints(event) {
            event.preventDefault();
            this.axios.delete("http://localhost:8081/points/",
            {
                headers: {"Authorization": "Bearer " + JSON.parse(localStorage.getItem("userDetails")).accessToken}
            }).then(() => {
              alert("WARNING: POINTS RESET!");
              this.getPoints();
            }).then(()=>{
              this.getPoints();
              this.$router.go();
            }).catch(error => {
                if (error.response.status === 401 || error.response.status === 403) {
                    this.invalidateTokenAndGoToAuthPage();
                }
            })
        },
        getPoints() {
            const userDetails = JSON.parse(localStorage.getItem("userDetails"));
            this.axios.get("http://localhost:8081/points/", {
                headers: {"Authorization": "Bearer " + userDetails.accessToken}
            }).then(response => {

              let pointsUnwrapped = new Array();
              for (let jsonObj of response.data._embedded.points) {
                pointsUnwrapped.push(
                    (({ x, y, r, hitResult, time }) => ({ x, y, r, hitResult, time }))(jsonObj)
                )
              }

              this.points = pointsUnwrapped;

              this.drawPoints();
            }).catch(error => {
                // alert(error.message);
                if (error.response.status === 401 || error.response.status === 403) {
                    this.invalidateTokenAndGoToAuthPage();
                }
            })
        },
        invalidateTokenAndGoToAuthPage() {
            localStorage.removeItem("userDetails");
            this.$router.push({name: "auth"});
        },
        drawPoints() {
          document.querySelectorAll('.div--point').forEach(point => point.remove());
          if(-3 <= this.r && this.r <= 5) {
            const coords = document.getElementById("coordsDiv");

            if (this.points) {
              for (let point of this.points) {
                let newElement = document.createElement("div");
                newElement.classList.add('div--point');
                newElement.style.position = 'absolute';
                newElement.style.width = '10px';
                newElement.style.height = '10px';
                newElement.style.backgroundColor = point.hitResult ? "green" : "red";

                let elX = point.x;
                let elY = point.y;


                elX /= this.r;
                elY /= this.r;

                elX = (elX / 2.5) + 0.5;
                elY = (elY / (-2.5)) + 0.5;

                elX *= 500;
                elY *= 500;

                elX -= 5;
                elY -= 5;

                newElement.style.left = "" + elX + "px";
                newElement.style.top = "" + elY + "px";

                if(elX <= 500 && elY <= 500 && elX >= 0 && elY >= 0) {
                  coords.appendChild(newElement);
                }
              }
            }
          }
        },
        sendPointFromCoords(event) {
          event.preventDefault();
          const coords = document.getElementById("coordsDiv");

          let rect = coords.getBoundingClientRect();
          let cx = event.clientX - rect.left;
          let cy = event.clientY - rect.top;

          cx = cx / 500;
          cy = cy / 500;

          cx = 2.5 * (cx - 0.5);
          cy = -2.5 * (cy - 0.5);

          cx *= this.r;
          cy *= this.r;

          this.axios.post("http://localhost:8081/points", {
                x: cx,
                y: cy,
                r: this.r
              },
              {
                headers: {"Authorization": "Bearer " + JSON.parse(localStorage.getItem("userDetails")).accessToken}
              }).then(() => {
            this.getPoints();
          }).catch(error => {
            if (error.response.status === 401) {
              this.invalidateTokenAndGoToAuthPage();
            }
          })
        },
        logout(event) {
            event.preventDefault();
            localStorage.removeItem("userDetails");
            this.$router.push({name: "auth"});
        }
    }
}

</script>

<style scoped>
#coordsDiv {
  background-image: url("../assets/Lab4Coordinates.png");
  background-size: cover;

  position: absolute;
  width: 500px;
  height: 500px;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

#tableDiv {
  position: absolute;
  margin: 0;
  padding: 0;

  width: 20%;
  height: 500px;
  top: 50%;
  right: 5%;
  transform: translate(0, -50%);

  background-color: yellow;
}

#inputDiv {
  position: absolute;
  margin: 0;
  padding: 0;

  width: 20%;
  height: 500px;
  top: 50%;
  left: 5%;
  transform: translate(0, -50%);

  background-color: yellow;
}

button {
  margin: 0; padding: 0; position: absolute;
}

.inputButton {
  width: 40%;
  height: 20%;
  bottom: 5%;
}

#submitButton {
  left: 5%;
}

#resetButton {
  right: 5%;
}

#logoutButton {
  position: absolute;
  margin: 0; padding: 0;
  top: 50px;
  left: 25px;
  width: 100px;
  height: 50px;
}

.rOnCoords {
  position: absolute;
  margin: 0;
  padding: 0;
}
#top2R, #topR, #bottom2R, #bottomR {
  left: 50%;
  transform: translate(0, -50%);
}
#left2R, #leftR, #rightR, #right2R {
  bottom: 50%;
  transform: translate(-50%, 0);
}
#top2R {top: 50px;} #topR {top: 150px;} #bottom2R {top: 450px;} #bottomR {top: 350px;}
#left2R {left: 50px;} #leftR {left: 150px;} #right2R {left: 450px;} #rightR {left: 350px;}

#result-table {
    width: 100%;
    text-align: center;
    /*border-collapse: collapse;*/
}

#result-table tr {
    height: 4%;
}

#result-table tr:nth-child(2n-1) {
    background-color: white;
}

#result-table tr:nth-child(2n):hover {
    background-color: cyan;
}

#result-table tr:nth-child(2n-1):hover {
    background-color: cyan;
}

#result-table tr.table-header:hover {
    background-color: cyan;
}

.coords-col {
    width: 12%;
}

.time-col {
    width: 25%;
}

@media only screen and (max-width: 777px) {
  #inputDiv {
    position: relative;
    width: 80%;
    height: 300px;
    top: 150px;
    left: 50%;
    transform: translate(-50%, 0);

    background-color: yellow;
  }

  #coordsDiv {
    background-image: url("../assets/Lab4Coordinates.png");
    background-size: cover;

    position: relative;
    width: 500px;
    height: 500px;

    top: 500px;
  }

  #tableDiv {
    position: relative;
    margin: 10px;
    padding: 10px;

    width: 80%;
    height: 300px;
    top: 300px;

    left: 50%;
    transform: translate(-50%, 0);

    background-color: yellow;
  }
}

@media only screen and (min-width: 778px) and (max-width: 1244px) {
  #inputDiv {
    position: relative;
    width: 80%;
    height: 300px;
    top: 150px;
    left: 50%;
    transform: translate(-50%, 0);

    background-color: yellow;
  }

  #coordsDiv {
    background-image: url("../assets/Lab4Coordinates.png");
    background-size: cover;

    position: relative;
    width: 500px;
    height: 500px;

    top: 500px;
  }

  #tableDiv {
    position: relative;
    margin: 10px;
    padding: 10px;

    width: 80%;
    height: 1000px;
    top: 500px;

    left: 50%;
    transform: translate(-50%, 0);

    background-color: yellow;
  }
}

</style>