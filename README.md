# gb_ansible

> lesson 1:
```log
alexandr@MacBook-Pro-Aleksandr gb_ansible % ansible local -m setup -a "filter=ansible_kernel" -v  
Using /Users/alexandr/Library/Mobile Documents/com~apple~CloudDocs/Documents/Projects/GeekBrains/gb_ansible/ansible.cfg as config file
[WARNING]: Platform darwin on host 127.0.0.1 is using the discovered Python interpreter at /Library/Frameworks/Python.framework/Versions/3.10/bin/python3.10, but future installation of another
Python interpreter could change the meaning of that path. See https://docs.ansible.com/ansible-core/2.13/reference_appendices/interpreter_discovery.html for more information.
127.0.0.1 | SUCCESS => {
"ansible_facts": {
"ansible_kernel": "21.6.0",
"discovered_interpreter_python": "/Library/Frameworks/Python.framework/Versions/3.10/bin/python3.10"
},
"changed": false
}
```
