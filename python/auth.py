import time
import datetime
import uuid
import hmac
import hashlib


def unique_id():
    return str(uuid.uuid1().hex)


def get_iso_datetime():
    utc_offset_sec = time.altzone if time.localtime().tm_isdst else time.timezone
    utc_offset = datetime.timedelta(seconds=-utc_offset_sec)
    return datetime.datetime.now().replace(tzinfo=datetime.timezone(offset=utc_offset)).isoformat()


def get_signature(key, msg):
    return hmac.new(key.encode(), msg.encode(), hashlib.sha256).hexdigest()
