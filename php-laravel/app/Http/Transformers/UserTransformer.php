<?php

namespace App\Http\Transformers;


use App\User;
use League\Fractal\TransformerAbstract;

class UserTransformer extends TransformerAbstract
{
    public function transform(User $user)
    {
        return [
            'id' => (int)$user->id,
            'firstName' => (string)$user->firstName,
            'lastName' => (string)$user->lastName,
            'countries' => $user->countries,
        ];
    }
}
