const bookshelf = require('../../bookshelf');
const Model = bookshelf.Model;

class User extends Model
{
	get tableName() {
		return 'users';
	}

	countries() {
		return this.belongsToMany('Country', 'user_country_mapping', 'user_id', 'country_id');
	}
}

module.exports = bookshelf.model('User', User);
