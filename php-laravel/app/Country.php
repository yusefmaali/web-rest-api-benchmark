<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Country extends Model
{
    protected $table = 'countries';
    protected $hidden = array('pivot');

    public function users()
    {
        return $this->belongsToMany('App\User', 'user_country_mapping', 'country_id', 'user_id');
    }
}
