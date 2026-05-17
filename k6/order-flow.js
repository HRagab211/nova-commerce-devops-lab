import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  vus: 5,
  duration: '30s',
};

const BASE_URL = __ENV.BASE_URL || 'http://localhost:8080';
const API_KEY = __ENV.LAB_API_KEY || 'devops-lab-key';

export default function () {
  const response = http.get(`${BASE_URL}/api/devops/lab-check`, {
    headers: { 'X-Lab-Key': API_KEY },
  });

  check(response, {
    'lab check is 200': (r) => r.status === 200,
  });

  sleep(1);
}
