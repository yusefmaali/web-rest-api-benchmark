import falcon
from falcon import API
from sqlalchemy import create_engine
from sqlalchemy.orm import scoped_session
from sqlalchemy.orm import sessionmaker
from .app import HelloWorldResource, ComputeResource, CountriesResource, UsersResource


class SQLAlchemySessionManager:
    def __init__(self, Session):
        self.Session = Session

    def process_resource(self, req, resp, resource, params):
        resource.session = self.Session()

    def process_response(self, req, resp, resource, req_succeeded):
        if hasattr(resource, 'session'):
            resource.session.close()


def get_app() -> API:
    engine = create_engine("mysql+mysqldb://benchmark:benchmark@db/benchmark")

    session_factory = sessionmaker(bind=engine)
    Session = scoped_session(session_factory)

    app = falcon.API(middleware=[
        SQLAlchemySessionManager(Session),
    ])

    app.add_route('/hello', HelloWorldResource())
    app.add_route('/compute', ComputeResource())
    app.add_route('/countries', CountriesResource())
    app.add_route('/users', UsersResource())

    return app


application = get_app()
