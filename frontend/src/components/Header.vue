<template>
    <div class="header">
        {{fullName}}
    </div>
</template>

<script>
export default {
    name: "Header",
    props: {
        variant: String,
        group: String,
        firstName: String,
        lastName: String
    },
    computed: {
        fullName() {
            return "Androsov Ivan P3210 v.10773";
        }
    }
}
</script>

<style scoped>

body {
  margin: 0;
  padding: 0;
}

.header{
  margin: 0;
  padding: 0;
  background-color: yellow;

  position: absolute;
  width: 100%;
  height: 50px;
  top: 50px;
  left: 0%;

  text-align: center;
  font-size: 2vw;
}

</style>