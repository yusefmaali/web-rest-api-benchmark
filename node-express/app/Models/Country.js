const bookshelf = require('../../bookshelf');
const Model = bookshelf.Model;

class Country extends Model
{
    get tableName() {
		return 'countries';
	}

	users() {
		return this.belongsToMany('User', 'user_country_mapping', 'country_id', 'user_id');
	}
}

module.exports = bookshelf.model('Country', Country);
