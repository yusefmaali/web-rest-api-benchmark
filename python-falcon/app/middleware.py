class SQLAlchemySessionManager:
    def __init__(self, Session):
        self.Session = Session

    def process_resource(self, req, resp, resource, params):
        resource.session = self.Session()

    def process_response(self, req, resp, resource, req_succeeded):
        if hasattr(resource, 'session'):
            Session.remove()