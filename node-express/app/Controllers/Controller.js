const Country = require('../Models/Country');
const User = require('../Models/User');

class Controller 
{
	hello(req, res) {
		return res.json({ hello: 'world' });
	}

	compute(req, res) {
		let x = 0, y = 1;

		let max = 10000 + Math.random() * 500;

		for (let i = 0; i <= max; i++) {
		    let z = x + y;
		    x = y;
		    y = z;
		}

		return res.json({ status: 'done' })
	}

	async countries(req, res) {
		let data = await Country.fetchAll();

		return res.json(data);
	}

	async users(req, res) {
		let data = await User.query(q => {
				q.innerJoin('user_country_mapping', 'users.id', 'user_country_mapping.user_id');
				q.innerJoin('countries', 'user_country_mapping.country_id', 'countries.id');
				q.groupBy('users.id');
				q.where('countries.name', 'France');
			})
			.fetchAll({
			 	withRelated: ['countries']
			});

		return res.json(data.toJSON({ omitPivot: true }));
	}
}

module.exports = new Controller();
