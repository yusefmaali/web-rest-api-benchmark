<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class User extends Model
{
    protected $table = 'users';

    public function countries()
    {
        return $this->belongsToMany('App\Country', 'user_country_mapping', 'user_id', 'country_id');
    }
}
