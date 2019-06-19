package models

import (
	"fmt"
)

//a struct to rep user account
type User struct {
	Id        uint       `json:"id"`
	Email     string     `json:"email"`
	FirstName string     `json:"first_name"`
	LastName  string     `json:"last_name"`
	Countries []*Country `gorm:"many2many:user_country_mapping" json:"countries"`
}

func GetUsersByCountryName(countryName string) ([]*User) {
	country := &Country{}
	err := GetDB().
		Preload("Users").
		Preload("Users.Countries").Where(&Country{Name: countryName}).
		Find(country).
		Error
	if err != nil {
		fmt.Println(err)
		return nil
	}
	return country.Users
}
