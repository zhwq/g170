### 检查

```bash
# RHEL/CentOS/Fedora:
sudo yum install autoconf automake libtool make tar \
                 glibc-devel \
                 libgcc.i686 glibc-devel.i686
```

出现错误

```log
错误： Multilib version problems found. This often means that the root
      cause is something else and multilib version checking is just
      pointing out that there is a problem. Eg.:

        1. You have an upgrade for libgcc which is missing some
           dependency that another package requires. Yum is trying to
           solve this by installing an older version of libgcc of the
           different architecture. If you exclude the bad architecture
           yum will tell you what the root cause is (which package
           requires what). You can try redoing the upgrade with
           --exclude libgcc.otherarch ... this should give you an error
           message showing the root cause of the problem.

        2. You have multiple architectures of libgcc installed, but
           yum can only see an upgrade for one of those architectures.
           If you don't want/need both architectures anymore then you
           can remove the one with the missing update and everything
           will work.

        3. You have duplicate versions of libgcc installed already.
           You can use "yum check" to get yum show these errors.

      ...you can also use --setopt=protected_multilib=false to remove
      this checking, however this is almost never the correct thing to
      do as something else is very likely to go wrong (often causing
      much more problems).

      保护多库版本：libgcc-4.8.5-44.el7.i686 != libgcc-4.8.5-36.el7_6.2.x86_64
```

处理

```bash
yum install libgcc-4.8.5-36.el7_6.2.x86_64
```


/*
Handles a server-side channel.
<p/>
a protocal
  that discards any received data without any response
<p/>
-- md
- ChannelInboundHandler
- ByteBuf a reference-couted object which has to be released explicitly via the release() method
 */
