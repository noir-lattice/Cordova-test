export function dateFormat(date: Date, format: string = "YYYY-MM-DD HH:mm:ss") {
  const o = {
    "M+": date.getMonth() + 1, // month
    "D+": date.getDate(), // day
    "H+": date.getHours(), // hour
    "m+": date.getMinutes(), // minute
    "s+": date.getSeconds(), // second
    "q+": Math.floor((date.getMonth() + 3) / 3), // quarter
    S: date.getMilliseconds(), // millisecond
    w: "日一二三四五六".charAt(date.getDay()),
  };

  format = format
    .replace(/Y{4}/, date.getFullYear().toString())
    .replace(/Y{2}/, date.getFullYear().toString().substring(2));

  let k, reg;
  for (k in o) {
    reg = new RegExp(k);

    format = format.replace(reg, match);
  }

  function match(m) {
    return m.length === 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length);
  }

  return format;
}
