# ansible playbook практики

1. Начинайте с декларативного языка, а не c программного интерфейса. Начните с определения результатов, которые вы собираетесь получить, а не определения того, как вы можете получить эти результаты.

2. Пишите грамотные и понятные сценарии. Ваши сценарии должны быть легки для чтения и понимания. Задавайте вопросы вроде: «Что этот таск делает? Какие настройки от него ожидаются в результате?»

3. Делите задачу на две части: предварительные проверки и обработку. Отдельно задавайте такие задачи, как проверка требований и готовности и такие задачи как установка, проверка и работа.

4. Используйте модульное и инкрементальное программирование. Для бо́льших тасков, например, если вам нужно установить приложение, лучше разделить процесс на множество маленьких тасков, которые можно выполнить по одному.

5. Используйте отладочные инструменты, чтобы проверить последовательность и результаты тасков.

6. Отрефакторите ваш код: удалите лишние задачи, завершите проработку тасков, замените переменные и убедитесь, что логика явна и проста.

7. Протестируйте ваши Ansible-playbook-сценарии до реальной деплой. Используйте набор инструментов и проверьте ваши playbook'и перед выполнением.

8. Поддерживайте ваши Ansible Playbook'и. Следите за изменениями и обновляйте проекты, чтобы избежать несовместимости.

примеры хорошо описанных типовых  playbook можно найти здесь
https://docs.ansible.com/ansible/latest/user_guide/playbooks_best_practices.html

примеры playbook:

```
--
- name: Install and configure Apache web server
  hosts: web
  tasks:
    - name: Check for existing install
      tags: install
      command: 'apache2 -v'
      register: apache_check

    - name: Install Apache web server
      tags: install
      apt: name=apache2 state=present
      when: apache_check.rc != 0

    - name: Configure Apache settings
      tags: configure
      template: src=templates/apache.conf.j2 dest=/etc/apache2/apache2.conf
      notify: restart apache

далее можно использовать модули для автоматизации Ansible:

--
- name: Install and configure Apache web server
  hosts: web
  tasks:
    - name: Check for existing install
      tags: install
      command: 'apache2 -v'
      register: apache_check

    - name: Install Apache web server
      tags: install
      apt:
      name: "{{ item }}"
      state: present
      with_items:
        - apache2
        - libapache2-mod-wsgi
          when: apache_check.rc != 0

    - name: Configure Apache settings
      tags: configure
      template: src=templates/apache.conf.j2 dest=/etc/apache2/apache2.conf
      notify: restart apache

еще примеры:

- name: Setup firewall
  hosts: firewalld
  tasks:
    - name: Install and start firewall
      tags: install
      service:
      name: firewalld
      state: started
      enabled: yes

    - name: Add SSH to zone
      tags: configure
      firewalld:
      zone: public
      service: ssh
      state: enabled

пример установки owncloud

--
- name: Install OwnCloud Server
  hosts: web
  tasks:
    - name: Ensure necessary packages are installed
      tags: install
      apt:
      package:
      - apache2
      - php
      - owncloud
      state: present
      notify: restart apache

    - name: Configure the web server
      tags: configure
      template:
      src: templates/owncloud.conf.j2
      dest: /etc/apache2/owncloud.conf
      notify: restart apache

    - name: Update Apache configuration
      tags: configure
      lineinfile:
      dest: /etc/apache2/apache2.conf
      line: "Include /etc/apache2/owncloud.conf"
      notify: restart apache

```

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
        "│ └── app_configuration.conf",
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