package main

import (
	"fmt"
	"github.com/gorilla/mux"
	"github.com/sotomskir/go-mux-rest-api-benchmark/controllers"
	"net/http"
	"os"
)

func main() {

	router := mux.NewRouter()

	router.HandleFunc("/hello", controllers.Hello).Methods("GET")
	router.HandleFunc("/compute", controllers.Compute).Methods("GET")
	router.HandleFunc("/users", controllers.Users).Methods("GET")
	router.HandleFunc("/countries", controllers.Countries).Methods("GET")

	port := os.Getenv("PORT")
	if port == "" {
		port = "8000"
	}

	fmt.Println(port)

	err := http.ListenAndServe(":" + port, router)
	if err != nil {
		fmt.Print(err)
	}
}
