const PROD_BACKEND_API_URL = "/api";
const DEV_BACKEND_API_URL = "http://16.16.98.218:80/api";

export const BACKEND_API_URL =
	process.env.NODE_ENV === "development" ? DEV_BACKEND_API_URL : PROD_BACKEND_API_URL;