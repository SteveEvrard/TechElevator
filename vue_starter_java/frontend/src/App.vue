<template>
  <div id="app">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Great+Vibes" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <div class="main-logo">
      <img src="@/assets/img/TKWS_Black Logo.png">
    </div>
    <router-view/>
    <br>
  </div>
</template>


<style>
body {
  font-family: "Open Sans", sans-serif;
  background-color: black;
}

.main-logo {
  display: flex;
  align-content: center;
  justify-content: center;
  background: transparent;
}

.main-logo img {
  max-width: 300px;
  min-width: 40px;
  min-height: 40px;
  padding: 5%;
  object-fit: contain;
  filter: invert(100%);
}
.nav,
.admin-nav {
  background-color: #2e4d58;
  text-align: center;
  padding: 30px 0px 30px 0px;
}
/*links for anyone*/
.nav-link {
  color: white;
  text-decoration: none;
  font-weight: bold;
  padding: 5%;
  font-size: 2em;
}
/*links for admin when logged in due to number of them to make them fit */
.admin-nav-link {
  color: white;
  text-decoration: none;
  font-weight: bold;
  padding: 5%;
  font-size: 1.7em;
}

.admin-nav-link:hover {
  text-decoration: underline;
  color: #9d432c;
}

.nav-link:hover {
  text-decoration: underline;
  color: #9d432c;
}
</style>
