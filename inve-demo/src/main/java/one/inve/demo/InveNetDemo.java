package one.inve.demo;

import static java.util.stream.Collectors.joining;

import one.inve.cluster.Cluster;
import one.inve.cluster.ClusterConfig;
import one.inve.cluster.Member;
import one.inve.transport.Address;

public class InveNetDemo {

	public static void main(String[] args) {
		//顶层全节点
		ClusterConfig seedConfig = ClusterConfig.builder()
		        .listenAddress("192.168.10.129")
		        .port(20000)
		        .build();
		Cluster seed = Cluster.joinAwait(seedConfig);
		
		//局部全节点
		ClusterConfig NodeConfig01 = ClusterConfig.builder()
				.seedMembers(seed.address())
		        .listenAddress("192.168.10.129")
		        .port(15001)
		        .addMetadata("slice","01") //01分片
		        .addMetadata("pubkey","xxxx") //公钥
		        .build();
		Cluster node01 = Cluster.joinAwait(NodeConfig01);
		
		ClusterConfig NodeConfig02 = ClusterConfig.builder()
				.seedMembers(seed.address())
		        .listenAddress("192.168.10.129")
		        .port(15002)
		        .addMetadata("slice","01") //01分片
		        .addMetadata("pubkey","xxxx") //公钥
		        .build();
		Cluster node02 = Cluster.joinAwait(NodeConfig02);
		
		
		ClusterConfig NodeConfig03 = ClusterConfig.builder()
				.seedMembers(seed.address())
		        .listenAddress("192.168.10.129")
		        .port(15003)
		        .addMetadata("slice","02") //02分片
		        .addMetadata("pubkey","xxxx") //公钥
		        .build();
		Cluster node03 = Cluster.joinAwait(NodeConfig03);
		
		System.out.println("Node01 (" + node01.address() + ") cluster: "
		        + node01.members().stream().map(Member::toString).collect(joining("\n", "\n", "\n")));
		
		System.out.println("Node02 (" + node02.address() + ") cluster: "
		        + node02.members().stream().map(Member::toString).collect(joining("\n", "\n", "\n")));
		
		System.out.println("Node03 (" + node03.address() + ") cluster: "
		        + node03.members().stream().map(Member::toString).collect(joining("\n", "\n", "\n")));
		System.out.println("otherMembers接口返回不包含自身的邻居列表 (" + node02.address() + ") cluster: "
		        + node02.otherMembers().stream().map(Member::toString).collect(joining("\n", "\n", "\n")));
		
		System.out.println("findMembersBySliceIda接口返回片内邻居 (" + node03.address() + ") cluster: "
		        + node03.findMembersBySliceId("01").stream().map(Member::toString).collect(joining("\n", "\n", "\n")));
		
		Member member = node03.member(Address.create("192.168.10.129", 20000)).get();
		
	}

}
