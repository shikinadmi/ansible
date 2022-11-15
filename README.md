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

> lesson 2:
```log
alexandr@MacBook-Pro-Aleksandr gb_ansible % ./run.sh ya_cloud
[WARNING]: No inventory was parsed, only implicit localhost is available
[WARNING]: provided hosts list is empty, only localhost is available. Note that the implicit localhost does not match 'all'
[WARNING]: Could not match supplied host pattern, ignoring: ya_cloud
[WARNING]: Found both group and host with same name: ya_cloud

PLAY [get env and release version] ******************************************************************************************************************************************************************

TASK [Gathering Facts] ******************************************************************************************************************************************************************************
ok: [ya_cloud]

TASK [create_users : create group "testuser"] *******************************************************************************************************************************************************
ok: [ya_cloud]

TASK [create_users : create user "testuser" group "testuser"] ***************************************************************************************************************************************
ok: [ya_cloud]

TASK [configuration : create directory configuration] ***********************************************************************************************************************************************
ok: [ya_cloud]

TASK [configuration : create file configuration] ****************************************************************************************************************************************************
changed: [ya_cloud]

TASK [configuration : create configuration file app] ************************************************************************************************************************************************
ok: [ya_cloud]

TASK [configuration : get_url] **********************************************************************************************************************************************************************
changed: [ya_cloud]

RUNNING HANDLER [configuration : install dnf utils] *************************************************************************************************************************************************
ok: [ya_cloud] => (item=tree)

RUNNING HANDLER [configuration : tree] **************************************************************************************************************************************************************
changed: [ya_cloud]

RUNNING HANDLER [configuration : debug] *************************************************************************************************************************************************************
ok: [ya_cloud] => {
    "msg": [
        "/home/testuser",
        "├── configuration",
        "│   └── app_configuration.conf",
        "└── README.md",
        "",
        "1 directory, 2 files",
        "-rw-r--r--. 1 testuser testuser 82 Oct 15 20:16 /home/testuser/configuration/app_configuration.conf",
        "# BEGIN ANSIBLE MANAGED BLOCK",
        "test configuration file",
        "# END ANSIBLE MANAGED BLOCK"
    ]
}

PLAY RECAP ******************************************************************************************************************************************************************************************
ya_cloud                   : ok=10   changed=3    unreachable=0    failed=0    skipped=0    rescued=0    ignored=0   


```