use libc::size_t;
use std::ffi::{CStr, CString};
use std::os::raw::c_char;

#[no_mangle]
pub unsafe extern "C" fn greet(name: *const c_char, message: *mut c_char, count: size_t) {
    let name = CStr::from_ptr(name);
    let name = name.to_str().unwrap();
    let text = format!("こんにちは、{}！", name);
    let text = CString::new(text).unwrap();

    message.copy_from(text.as_ptr(), count);
}

#[cfg(test)]
mod tests {
    use super::greet;
    use libc::size_t;
    use std::ffi::{CStr, CString};
    use std::os::raw::c_char;

    #[test]
    fn it_works() {
        let name = "錆";
        let name = CString::new(name).unwrap();
        let name = name.as_ptr();

        const BUFF_SIZE: size_t = 256;
        let mut message = ['\0' as c_char; BUFF_SIZE];

        unsafe {
            greet(name, message.as_mut_ptr(), BUFF_SIZE);
        }

        let message = unsafe { CStr::from_ptr(message.as_ptr()) };

        assert_eq!("こんにちは、錆！", message.to_str().unwrap());
    }
}
