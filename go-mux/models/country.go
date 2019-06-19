package models

import (
	"fmt"
)

type Country struct {
	Id    uint    `json:"id"`
	Name  string  `json:"name"`
	Users []*User `gorm:"many2many:user_country_mapping;" json:"-"`
}

func GetCountries() ([]*Country) {
	countries := make([]*Country, 0)
	err := GetDB().Find(&countries).Error
	if err != nil {
		fmt.Println(err)
		return nil
	}

	return countries
}
