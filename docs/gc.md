```java
java -XX:+PrintGCDetails demo/jvm02/GCLogAnalysis
java -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps demo/jvm02/GCLogAnalysis
java -Xmx128m -XX:+PrintGCDetails -XX:+PrintGCDateStamps demo/jvm02/GCLogAnalysis
java -Xmx512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps demo/jvm02/GCLogAnalysis
java -Xmx1g -XX:+PrintGCDetails -XX:+PrintGCDateStamps demo/jvm02/GCLogAnalysis
java -Xmx2g -XX:+PrintGCDetails -XX:+PrintGCDateStamps demo/jvm02/GCLogAnalysis
java -Xmx4g -XX:+PrintGCDetails -XX:+PrintGCDateStamps demo/jvm02/GCLogAnalysis
λ java -XX:+PrintGCDetails -XX:+PrintHeapAtGC -XX:+PrintGCTimeStamps demo/jvm02/GCLogAnalysis
```

>同一个参数 多次执行 生成对象次数也不一样

```java
λ java -XX:+UseSerialGC -Xmx512m -Xms512m -XX:+PrintGCDetails demo/jvm02/GCLogAnalysis
正在执行...
[GC (Allocation Failure) [DefNew: 139671K->17472K(157248K), 0.0248984 secs] 139671K->47208K(506816K), 0.0258241 secs] [Times: user=0.00 sys=0.01, real=0.03 secs]
[GC (Allocation Failure) [DefNew: 157248K->17469K(157248K), 0.0286943 secs] 186984K->82494K(506816K), 0.0295153 secs] [Times: user=0.03 sys=0.00, real=0.03 secs]
[GC (Allocation Failure) [DefNew: 157245K->17471K(157248K), 0.0274453 secs] 222270K->127401K(506816K), 0.0282459 secs] [Times: user=0.03 sys=0.00, real=0.03 secs]
[GC (Allocation Failure) [DefNew: 157247K->17470K(157248K), 0.0315580 secs] 267177K->173032K(506816K), 0.0324795 secs] [Times: user=0.03 sys=0.00, real=0.03 secs]
[GC (Allocation Failure) [DefNew: 157246K->17470K(157248K), 0.0283475 secs] 312808K->222986K(506816K), 0.0294867 secs] [Times: user=0.00 sys=0.03, real=0.03 secs]
[GC (Allocation Failure) [DefNew: 157206K->17470K(157248K), 0.0287660 secs] 362721K->270529K(506816K), 0.0296906 secs] [Times: user=0.00 sys=0.02, real=0.03 secs]
[GC (Allocation Failure) [DefNew: 157246K->17471K(157248K), 0.0274820 secs] 410305K->310267K(506816K), 0.0284935 secs] [Times: user=0.02 sys=0.02, real=0.03 secs]
[GC (Allocation Failure) [DefNew: 157178K->17471K(157248K), 0.0247380 secs] 449973K->351396K(506816K), 0.0253504 secs] [Times: user=0.02 sys=0.00, real=0.03 secs]
[GC (Allocation Failure) [DefNew: 156929K->156929K(157248K), 0.0006939 secs][Tenured: 333925K->271294K(349568K), 0.0406448 secs] 490854K->271294K(506816K), [Metaspace: 2765K->2765K(1056768K)], 0.0425341 secs] [Times: user=0.03 sys=0.00, real=0.04 secs]
[GC (Allocation Failure) [DefNew: 139776K->17470K(157248K), 0.0094889 secs] 411070K->326614K(506816K), 0.0100582 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [DefNew: 157246K->157246K(157248K), 0.0006139 secs][Tenured: 309144K->307087K(349568K), 0.0463069 secs] 466390K->307087K(506816K), [Metaspace: 2766K->2766K(1056768K)], 0.0485826 secs] [Times: user=0.05 sys=0.00, real=0.05 secs]
[GC (Allocation Failure) [DefNew: 139690K->139690K(157248K), 0.0005396 secs][Tenured: 307087K->316646K(349568K), 0.0501461 secs] 446778K->316646K(506816K), [Metaspace: 2766K->2766K(1056768K)], 0.0515984 secs] [Times: user=0.05 sys=0.00, real=0.05 secs]
[GC (Allocation Failure) [DefNew: 139776K->139776K(157248K), 0.0002800 secs][Tenured: 316646K->306145K(349568K), 0.0492576 secs] 456422K->306145K(506816K), [Metaspace: 2766K->2766K(1056768K)], 0.0504549 secs] [Times: user=0.05 sys=0.00, real=0.05 secs]
[GC (Allocation Failure) [DefNew: 139548K->17471K(157248K), 0.0066248 secs] 445694K->351238K(506816K), 0.0073375 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [DefNew: 157247K->157247K(157248K), 0.0007013 secs][Tenured: 333767K->334522K(349568K), 0.0381788 secs] 491014K->334522K(506816K), [Metaspace: 2766K->2766K(1056768K)], 0.0402262 secs] [Times: user=0.03 sys=0.00, real=0.04 secs]
[GC (Allocation Failure) [DefNew: 139776K->139776K(157248K), 0.0004804 secs][Tenured: 334522K->343115K(349568K), 0.0554759 secs] 474298K->343115K(506816K), [Metaspace: 2766K->2766K(1056768K)], 0.0570381 secs] [Times: user=0.05 sys=0.00, real=0.06 secs]
[GC (Allocation Failure) [DefNew: 139776K->139776K(157248K), 0.0003633 secs][Tenured: 343115K->344147K(349568K), 0.0550806 secs] 482891K->344147K(506816K), [Metaspace: 2766K->2766K(1056768K)], 0.0566426 secs] [Times: user=0.05 sys=0.00, real=0.06 secs]
执行结束!共生成对象次数:8953
Heap
 def new generation   total 157248K, used 6012K [0x00000000e0000000, 0x00000000eaaa0000, 0x00000000eaaa0000)
  eden space 139776K,   4% used [0x00000000e0000000, 0x00000000e05df380, 0x00000000e8880000)
  from space 17472K,   0% used [0x00000000e8880000, 0x00000000e8880000, 0x00000000e9990000)
  to   space 17472K,   0% used [0x00000000e9990000, 0x00000000e9990000, 0x00000000eaaa0000)
 tenured generation   total 349568K, used 344147K [0x00000000eaaa0000, 0x0000000100000000, 0x0000000100000000)
   the space 349568K,  98% used [0x00000000eaaa0000, 0x00000000ffab4e90, 0x00000000ffab5000, 0x0000000100000000)
 Metaspace       used 2772K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 306K, capacity 386K, committed 512K, reserved 1048576K

λ java -XX:+UseParallelGC -Xmx512m -Xms512m -XX:+PrintGCDetails demo/jvm02/GCLogAnalysis
正在执行...
[GC (Allocation Failure) [PSYoungGen: 131584K->21500K(153088K)] 131584K->41053K(502784K), 0.0082092 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 153084K->21493K(153088K)] 172637K->87291K(502784K), 0.0170905 secs] [Times: user=0.00 sys=0.00, real=0.02 secs]
[GC (Allocation Failure) [PSYoungGen: 153077K->21503K(153088K)] 218875K->128283K(502784K), 0.0152932 secs] [Times: user=0.03 sys=0.09, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 153087K->21498K(153088K)] 259867K->170813K(502784K), 0.0156775 secs] [Times: user=0.05 sys=0.08, real=0.02 secs]
[GC (Allocation Failure) [PSYoungGen: 153082K->21489K(153088K)] 302397K->211546K(502784K), 0.0104428 secs] [Times: user=0.05 sys=0.08, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 153073K->21488K(80384K)] 343130K->255907K(430080K), 0.0120049 secs] [Times: user=0.13 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 80368K->34642K(116736K)] 314787K->273832K(466432K), 0.0083509 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 93522K->46213K(116736K)] 332712K->292848K(466432K), 0.0098006 secs] [Times: user=0.06 sys=0.05, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 104754K->55894K(116736K)] 351388K->310538K(466432K), 0.0131340 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 114724K->36489K(116736K)] 369369K->325780K(466432K), 0.0114677 secs] [Times: user=0.08 sys=0.03, real=0.01 secs]
[Full GC (Ergonomics) [PSYoungGen: 36489K->0K(116736K)] [ParOldGen: 289291K->231031K(349696K)] 325780K->231031K(466432K), [Metaspace: 2766K->2766K(1056768K)], 0.0529295 secs] [Times: user=0.31 sys=0.00, real=0.05 secs]
[GC (Allocation Failure) [PSYoungGen: 58659K->20638K(116736K)] 289690K->251669K(466432K), 0.0035330 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 79518K->20127K(116736K)] 310549K->271029K(466432K), 0.0058502 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 79007K->18842K(116736K)] 329909K->288486K(466432K), 0.0060301 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 77276K->19976K(116736K)] 346919K->306800K(466432K), 0.0058897 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 78856K->17748K(116736K)] 365680K->322966K(466432K), 0.0095763 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[Full GC (Ergonomics) [PSYoungGen: 17748K->0K(116736K)] [ParOldGen: 305218K->266223K(349696K)] 322966K->266223K(466432K), [Metaspace: 2766K->2766K(1056768K)], 0.0489547 secs] [Times: user=0.33 sys=0.02, real=0.05 secs]
[GC (Allocation Failure) [PSYoungGen: 58880K->24467K(116736K)] 325103K->290691K(466432K), 0.0042170 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 83347K->14541K(116736K)] 349571K->303389K(466432K), 0.0075632 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 73320K->19963K(116736K)] 362168K->321712K(466432K), 0.0077699 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 78843K->21045K(116736K)] 380592K->340929K(466432K), 0.0094373 secs] [Times: user=0.08 sys=0.02, real=0.01 secs]
[Full GC (Ergonomics) [PSYoungGen: 21045K->0K(116736K)] [ParOldGen: 319884K->284903K(349696K)] 340929K->284903K(466432K), [Metaspace: 2766K->2766K(1056768K)], 0.0516235 secs] [Times: user=0.36 sys=0.00, real=0.05 secs]
[GC (Allocation Failure) [PSYoungGen: 58880K->19755K(116736K)] 343783K->304658K(466432K), 0.0034191 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 78618K->19885K(116736K)] 363521K->322195K(466432K), 0.0057905 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [PSYoungGen: 78764K->18002K(116736K)] 381075K->338361K(466432K), 0.0064716 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[Full GC (Ergonomics) [PSYoungGen: 18002K->0K(116736K)] [ParOldGen: 320359K->294363K(349696K)] 338361K->294363K(466432K), [Metaspace: 2766K->2766K(1056768K)], 0.0607188 secs] [Times: user=0.33 sys=0.00, real=0.06 secs]
[GC (Allocation Failure) [PSYoungGen: 58880K->19569K(116736K)] 353243K->313932K(466432K), 0.0042212 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 77842K->19082K(116736K)] 372205K->331756K(466432K), 0.0054966 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 77833K->20639K(116736K)] 390507K->352108K(466432K), 0.0057021 secs] [Times: user=0.06 sys=0.06, real=0.00 secs]
[Full GC (Ergonomics) [PSYoungGen: 20639K->0K(116736K)] [ParOldGen: 331468K->301852K(349696K)] 352108K->301852K(466432K), [Metaspace: 2766K->2766K(1056768K)], 0.0501000 secs] [Times: user=0.22 sys=0.00, real=0.05 secs]
[GC (Allocation Failure) [PSYoungGen: 58880K->19587K(116736K)] 360732K->321440K(466432K), 0.0035610 secs] [Times: user=0.11 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [PSYoungGen: 78467K->24416K(116736K)] 380320K->344421K(466432K), 0.0063127 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[Full GC (Ergonomics) [PSYoungGen: 24416K->0K(116736K)] [ParOldGen: 320004K->311633K(349696K)] 344421K->311633K(466432K), [Metaspace: 2766K->2766K(1056768K)], 0.0558694 secs] [Times: user=0.41 sys=0.01, real=0.06 secs]
执行结束!共生成对象次数:7932
Heap
 PSYoungGen      total 116736K, used 58840K [0x00000000f5580000, 0x0000000100000000, 0x0000000100000000)
  eden space 58880K, 99% used [0x00000000f5580000,0x00000000f8ef6318,0x00000000f8f00000)
  from space 57856K, 0% used [0x00000000f8f00000,0x00000000f8f00000,0x00000000fc780000)
  to   space 57856K, 0% used [0x00000000fc780000,0x00000000fc780000,0x0000000100000000)
 ParOldGen       total 349696K, used 311633K [0x00000000e0000000, 0x00000000f5580000, 0x00000000f5580000)
  object space 349696K, 89% used [0x00000000e0000000,0x00000000f3054538,0x00000000f5580000)
 Metaspace       used 2772K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 306K, capacity 386K, committed 512K, reserved 1048576K

λ java -XX:+UseConcMarkSweepGC -Xmx512m -Xms512m -XX:+PrintGCDetails demo/jvm02/GCLogAnalysis
正在执行...
[GC (Allocation Failure) [ParNew: 139776K->17471K(157248K), 0.0091072 secs] 139776K->46612K(506816K), 0.0099567 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[GC (Allocation Failure) [ParNew: 157247K->17472K(157248K), 0.0131360 secs] 186388K->90685K(506816K), 0.0137055 secs] [Times: user=0.11 sys=0.02, real=0.01 secs]
[GC (Allocation Failure) [ParNew: 157248K->17470K(157248K), 0.0318480 secs] 230461K->134676K(506816K), 0.0326232 secs] [Times: user=0.19 sys=0.05, real=0.03 secs]
[GC (Allocation Failure) [ParNew: 157246K->17467K(157248K), 0.0315102 secs] 274452K->179671K(506816K), 0.0320406 secs] [Times: user=0.24 sys=0.02, real=0.03 secs]
[GC (Allocation Failure) [ParNew: 157243K->17470K(157248K), 0.0290743 secs] 319447K->221376K(506816K), 0.0293922 secs] [Times: user=0.19 sys=0.05, real=0.03 secs]
[GC (CMS Initial Mark) [1 CMS-initial-mark: 203905K(349568K)] 221802K(506816K), 0.0004640 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-abortable-preclean-start]
[GC (Allocation Failure) [ParNew: 157246K->17471K(157248K), 0.0351772 secs] 361152K->267433K(506816K), 0.0355909 secs] [Times: user=0.31 sys=0.05, real=0.03 secs]
[GC (Allocation Failure) [ParNew: 157247K->17471K(157248K), 0.0349036 secs] 407209K->319100K(506816K), 0.0352017 secs] [Times: user=0.20 sys=0.02, real=0.04 secs]
[GC (Allocation Failure) [ParNew: 157247K->157247K(157248K), 0.0002382 secs][CMS[CMS-concurrent-abortable-preclean: 0.004/0.131 secs] [Times: user=0.61 sys=0.06, real=0.13 secs]
 (concurrent mode failure): 301628K->246148K(349568K), 0.0413659 secs] 458876K->246148K(506816K), [Metaspace: 2766K->2766K(1056768K)], 0.0425200 secs] [Times: user=0.03 sys=0.00, real=0.04 secs]
[GC (Allocation Failure) [ParNew: 139776K->17471K(157248K), 0.0071175 secs] 385924K->290716K(506816K), 0.0075451 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[GC (CMS Initial Mark) [1 CMS-initial-mark: 273244K(349568K)] 290860K(506816K), 0.0009836 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-abortable-preclean-start]
[GC (Allocation Failure) [ParNew: 157247K->17469K(157248K), 0.0227048 secs] 430492K->336775K(506816K), 0.0234566 secs] [Times: user=0.24 sys=0.00, real=0.02 secs]
[GC (Allocation Failure) [ParNew: 157245K->157245K(157248K), 0.0007609 secs][CMS[CMS-concurrent-abortable-preclean: 0.001/0.069 secs] [Times: user=0.28 sys=0.00, real=0.07 secs]
 (concurrent mode failure): 319305K->285086K(349568K), 0.0536024 secs] 476551K->285086K(506816K), [Metaspace: 2766K->2766K(1056768K)], 0.0554780 secs] [Times: user=0.06 sys=0.00, real=0.06 secs]
[GC (Allocation Failure) [ParNew: 139542K->17471K(157248K), 0.0076040 secs] 424628K->331620K(506816K), 0.0080876 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[GC (CMS Initial Mark) [1 CMS-initial-mark: 314149K(349568K)] 331908K(506816K), 0.0004642 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
[CMS-concurrent-abortable-preclean-start]
[CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[GC (CMS Final Remark) [YG occupancy: 42243 K (157248 K)][Rescan (parallel) , 0.0004326 secs][weak refs processing, 0.0001537 secs][class unloading, 0.0006437 secs][scrub symbol table, 0.0008710 secs][scrub string table, 0.0004942 secs][1 CMS-remark: 314149K(349568K)] 356393K(506816K), 0.0042384 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-sweep-start]
[CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-reset-start]
[CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [ParNew: 157247K->17470K(157248K), 0.0111935 secs] 429782K->335336K(506816K), 0.0118200 secs] [Times: user=0.13 sys=0.00, real=0.01 secs]
[GC (CMS Initial Mark) [1 CMS-initial-mark: 317866K(349568K)] 335659K(506816K), 0.0008558 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.004/0.004 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-abortable-preclean-start]
[CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[GC (CMS Final Remark) [YG occupancy: 40817 K (157248 K)][Rescan (parallel) , 0.0007206 secs][weak refs processing, 0.0000888 secs][class unloading, 0.0002983 secs][scrub symbol table, 0.0005407 secs][scrub string table, 0.0003079 secs][1 CMS-remark: 317866K(349568K)] 358683K(506816K), 0.0037457 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
[CMS-concurrent-sweep-start]
[CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-reset-start]
[CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [ParNew: 157206K->17471K(157248K), 0.0223472 secs] 441302K->345466K(506816K), 0.0229197 secs] [Times: user=0.06 sys=0.00, real=0.02 secs]
[GC (CMS Initial Mark) [1 CMS-initial-mark: 327994K(349568K)] 346207K(506816K), 0.0009722 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-abortable-preclean-start]
[CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[GC (CMS Final Remark) [YG occupancy: 49809 K (157248 K)][Rescan (parallel) , 0.0004796 secs][weak refs processing, 0.0000858 secs][class unloading, 0.0003611 secs][scrub symbol table, 0.0005354 secs][scrub string table, 0.0002158 secs][1 CMS-remark: 327994K(349568K)] 377803K(506816K), 0.0040762 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-sweep-start]
[CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[CMS-concurrent-reset-start]
[CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [ParNew: 157247K->17471K(157248K), 0.0158216 secs] 445940K->352645K(506816K), 0.0167264 secs] [Times: user=0.11 sys=0.00, real=0.02 secs]
[GC (CMS Initial Mark) [1 CMS-initial-mark: 335173K(349568K)] 355676K(506816K), 0.0006415 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.03 sys=0.00, real=0.00 secs]
[CMS-concurrent-abortable-preclean-start]
[CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[GC (CMS Final Remark) [YG occupancy: 47308 K (157248 K)][Rescan (parallel) , 0.0007456 secs][weak refs processing, 0.0001208 secs][class unloading, 0.0004559 secs][scrub symbol table, 0.0004712 secs][scrub string table, 0.0002262 secs][1 CMS-remark: 335173K(349568K)] 382482K(506816K), 0.0031849 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-sweep-start]
[CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-reset-start]
[CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [ParNew: 157247K->157247K(157248K), 0.0007182 secs][CMS: 297723K->326536K(349568K), 0.0653475 secs] 454971K->326536K(506816K), [Metaspace: 2766K->2766K(1056768K)], 0.0670468 secs] [Times: user=0.06 sys=0.00, real=0.07 secs]
[GC (CMS Initial Mark) [1 CMS-initial-mark: 326536K(349568K)] 327115K(506816K), 0.0004222 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-mark-start]
[CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-preclean-start]
[CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-abortable-preclean-start]
[CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[GC (CMS Final Remark) [YG occupancy: 36459 K (157248 K)][Rescan (parallel) , 0.0005401 secs][weak refs processing, 0.0000836 secs][class unloading, 0.0002597 secs][scrub symbol table, 0.0003516 secs][scrub string table, 0.0002533 secs][1 CMS-remark: 326536K(349568K)] 362995K(506816K), 0.0026524 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-sweep-start]
[CMS-concurrent-sweep: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[CMS-concurrent-reset-start]
[CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
[GC (Allocation Failure) [ParNew: 139776K->139776K(157248K), 0.0003939 secs][CMS: 325120K->337955K(349568K), 0.0688614 secs] 464896K->337955K(506816K), [Metaspace: 2766K->2766K(1056768K)], 0.0702826 secs] [Times: user=0.08 sys=0.00, real=0.07 secs]
[GC (CMS Initial Mark) [1 CMS-initial-mark: 337955K(349568K)] 338127K(506816K), 0.0004692 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
执行结束!共生成对象次数:9061
[CMS-concurrent-mark-start]
Heap
 par new generation   total 157248K, used 5735K [0x00000000e0000000, 0x00000000eaaa0000, 0x00000000eaaa0000)
  eden space 139776K,   4% used [0x00000000e0000000, 0x00000000e0599c10, 0x00000000e8880000)
  from space 17472K,   0% used [0x00000000e9990000, 0x00000000e9990000, 0x00000000eaaa0000)
  to   space 17472K,   0% used [0x00000000e8880000, 0x00000000e8880000, 0x00000000e9990000)
 concurrent mark-sweep generation total 349568K, used 337955K [0x00000000eaaa0000, 0x0000000100000000, 0x0000000100000000)
 Metaspace       used 2772K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 306K, capacity 386K, committed 512K, reserved 1048576K
[CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
```
