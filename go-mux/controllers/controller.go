package controllers

import (
	"encoding/json"
	"github.com/sotomskir/go-mux-rest-api-benchmark/models"
	"github.com/sotomskir/go-mux-rest-api-benchmark/utils"
	"math/rand"
	"net/http"
	"time"
)

var Hello = func(w http.ResponseWriter, r *http.Request) {
	utils.Respond(w, map[string]interface{}{"hello": "world"})
}

var Compute = func(w http.ResponseWriter, r *http.Request) {
	x := 0
	y := 1
	var (
		z   int
		max int
	)

	s1 := rand.NewSource(time.Now().UnixNano())
	r1 := rand.New(s1)
	max = 10000 + r1.Intn(500)

	for i := 0; i <= max; i++ {
		z = x + y
		x = y
		y = z
	}
	utils.Respond(w, map[string]interface{}{"status": "done"})
}

var Countries = func(w http.ResponseWriter, r *http.Request) {
	countries := models.GetCountries()
	w.Header().Add("Content-Type", "application/json")
	json.NewEncoder(w).Encode(countries)
}

var Users = func(w http.ResponseWriter, r *http.Request) {
	users := models.GetUsersByCountryName("France")
	w.Header().Add("Content-Type", "application/json")
	json.NewEncoder(w).Encode(users)
}
