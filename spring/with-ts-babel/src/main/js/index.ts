import { UAParser } from "ua-parser-js";

export const getOS = (userAgent: string) => {
  const parser = new UAParser(userAgent);
  return JSON.stringify(parser.getOS());
};
